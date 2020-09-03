package com.lishuai.spring.controller;

import com.alibaba.fastjson.JSONObject;
import com.lishuai.spring.config.DefContants;
import com.lishuai.spring.pojo.SysLoginModel;
import com.lishuai.spring.pojo.SysUser;
import com.lishuai.spring.service.ISysUserService;
import com.lishuai.spring.utils.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @Author scott
 * @since 2018-12-17
 */
@RestController
@RequestMapping("/sys")
@Api(tags = "用户登录")
@Slf4j
public class LoginController {
    @Autowired
    private ISysUserService sysUserService;
    @Autowired
    private RedisUtil redisUtil;

    private static final String BASE_CHECK_CODES = "qwertyuiplkjhgfdsazxcvbnmQWERTYUPLKJHGFDSAZXCVBNM1234567890";

    @Value("${login_cache_config.failure_times}")
    private long failureTimes;

    @Value("${login_cache_config.time_span}")
    private long timeSpan;

    @Value("${login_cache_config.lock_span}")
    private long lockSpan;

    private static final String FAILURE_TIMES_SUFFIX = "_failure_times";

    private static final String LOCK_SUFFIX = "_locked";

    @ApiOperation("登录接口")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Result<JSONObject> login(@RequestBody SysLoginModel sysLoginModel, HttpServletResponse response, HttpServletRequest request) {
        Result<JSONObject> result = new Result<JSONObject>();

        String username = sysLoginModel.getUsername();
        String password = sysLoginModel.getPassword();

        // 检查cookie是否存在，不存在默认为当前客户端第一次尝试登录，分发cookie，cookie用于不同客户端分开计数
        String loginCookie = null;
        Cookie[] cookies = request.getCookies();
        if (null != cookies && cookies.length != 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("loginCookie")) {
                    loginCookie = cookie.getValue();
                }
            }
        }
        if (loginCookie == null) {
            Cookie newCookie = new Cookie("loginCookie", UUID.randomUUID().toString().replaceAll("\\-", ""));
            loginCookie = newCookie.getValue();
            newCookie.setHttpOnly(true);
            newCookie.setSecure(true);
            newCookie.setMaxAge(60 * 60 * 24 * 30);
            newCookie.setPath("/stat");
            response.addCookie(newCookie);
        }
        //1. 校验用户是否有效
        SysUser sysUser = sysUserService.getUserByName(username);
        result = sysUserService.checkUserIsEffective(sysUser);
        if (!result.isSuccess()) {
            if (sysUser == null) {
                // 用户未注册情况与密码错误情况保持一致
                result = this.updateFailureTimesInRedis(username, loginCookie, result);
            }
            return result;
        }

        //2. 校验用户名或密码是否正确
        String userpassword = PasswordUtil.encrypt(username, password, sysUser.getSalt());
        String syspassword = sysUser.getPassword();
        if (!syspassword.equals(userpassword)) {
            return this.updateFailureTimesInRedis(username, loginCookie, result);
        }
        // 登录成功清除失败计数
        redisUtil.hdel(loginCookie + "_" + username + FAILURE_TIMES_SUFFIX, "count");
        //用户登录信息
        userInfo(sysUser, result);

        return result;
    }

    /**
     * 退出登录
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/logout")
    public Result<Object> logout(HttpServletRequest request, HttpServletResponse response) {
        //用户退出逻辑
        String token = request.getHeader(DefContants.X_ACCESS_TOKEN);
        if (oConvertUtils.isEmpty(token)) {
            return Result.error("退出登录失败！");
        }
        String username = JwtUtil.getUsername(token);
        SysUser sysUser = sysUserService.getUserByName(username);
        if (sysUser != null) {
            log.info(" 用户名:  " + sysUser.getRealname() + ",退出成功！ ");
            //清空用户登录Token缓存
            redisUtil.del(CommonConstant.PREFIX_USER_TOKEN + username + "_" + token);
            //清空用户登录Shiro权限缓存
            redisUtil.del(CommonConstant.PREFIX_USER_SHIRO_CACHE + sysUser.getId());
            //清空用户的缓存信息（包括部门信息），例如sys:cache:user::<username>
            redisUtil.del(String.format("%s::%s", CacheConstant.SYS_USERS_CACHE, sysUser.getUsername()));
            //调用shiro的logout
            SecurityUtils.getSubject().logout();
            return Result.ok("退出登录成功！");
        } else {
            return Result.error("Token无效!");
        }
    }

    /**
     * 更新用户登录错误计数
     *
     * @param username
     * @param result
     * @return
     */
    private Result<JSONObject> updateFailureTimesInRedis(String username, String loginCookie, Result<JSONObject> result) {
        String failureTimesRedisKey = loginCookie + "_" + username + FAILURE_TIMES_SUFFIX;
        String lockedRedisKey = loginCookie + "_" + username + LOCK_SUFFIX;
        if (redisUtil.hasKey(lockedRedisKey)) { // 如果已被锁定不更新
            long currentLockSpan = redisUtil.getExpire(lockedRedisKey);
            result.error500("该用户已锁定，请" + currentLockSpan / 60 + "分" + currentLockSpan % 60 + "秒后再试！");
            return result;
        }
        // 更新登录失败次数
        long currentFailureTimes = 1;
        if (!redisUtil.hHasKey(failureTimesRedisKey, "count")) { // redis中无当前用户的失败计数
            redisUtil.hset(failureTimesRedisKey, "count", 1, timeSpan * 60); // 使用hset避免更新计数重置过期时间
            log.info("用户{}当前登录失败次数：{}，剩余间隔计时：{} s", username, 1, redisUtil.getExpire(failureTimesRedisKey));
            result.error500("用户名或密码错误，错误累计" + failureTimes + "次，用户会被锁定" + lockSpan + "分钟，当前已累计错误" + currentFailureTimes + "次!");
        } else {
            currentFailureTimes = ((Integer) redisUtil.hget(failureTimesRedisKey, "count")).longValue();
            if (currentFailureTimes + 1 >= failureTimes) { //已达次数，锁定用户
                redisUtil.set(lockedRedisKey, null, lockSpan * 60);
                result.error500("用户名或密码错误，当前用户已累计错误" + failureTimes + "次，用户锁定" + lockSpan + "分钟，请稍后再试！");
            } else {// 未达锁定次数，计数加1
                currentFailureTimes += 1;
                redisUtil.hset(failureTimesRedisKey, "count", currentFailureTimes);
                log.info("用户{}当前登录失败次数：{}，剩余间隔计时：{} s", username, currentFailureTimes, redisUtil.getExpire(failureTimesRedisKey));
                result.error500("用户名或密码错误，错误累计" + failureTimes + "次，用户会被锁定" + lockSpan + "分钟，当前已累计错误" + currentFailureTimes + "次!");
            }
        }
        return result;
    }

    /**
     * 用户信息
     *
     * @param sysUser
     * @param result
     * @return
     */
    private Result<JSONObject> userInfo(SysUser sysUser, Result<JSONObject> result) {
        String syspassword = sysUser.getPassword();
        String username = sysUser.getUsername();
        // 生成token
        String token = JwtUtil.sign(username, syspassword);
        // 设置token缓存有效时间
        redisUtil.set(CommonConstant.PREFIX_USER_TOKEN + username + "_" + token, token);
        redisUtil.expire(CommonConstant.PREFIX_USER_TOKEN + username + "_" + token, JwtUtil.EXPIRE_TIME * 8 / 1000);
        JSONObject obj = new JSONObject();
        obj.put("token", token);
        obj.put("userInfo", sysUser);
        result.setResult(obj);
        result.success("登录成功");
        return result;
    }
}