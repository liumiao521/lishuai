package com.lishuai.spring.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lishuai.spring.mapper.SysPermissionMapper;
import com.lishuai.spring.mapper.SysRoleMapper;
import com.lishuai.spring.mapper.SysUserMapper;
import com.lishuai.spring.mapper.SysUserRoleMapper;
import com.lishuai.spring.pojo.LoginUser;
import com.lishuai.spring.pojo.SysPermission;
import com.lishuai.spring.pojo.SysUser;
import com.lishuai.spring.service.ISysRoleService;
import com.lishuai.spring.service.ISysUserService;
import com.lishuai.spring.utils.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @Author: scott
 * @Date: 2018-12-20
 */
@Service
@Slf4j
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private SysPermissionMapper sysPermissionMapper;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private ISysRoleService sysRoleService;

    @Override
    @Cacheable(cacheNames = CacheConstant.SYS_USERS_CACHE, key = "#username")
    public LoginUser getUserByName(String username, String temp) {
        if (oConvertUtils.isEmpty(username)) {
            return null;
        }
        LoginUser loginUser = new LoginUser();
        SysUser sysUser = sysUserMapper.getUserByName(username);
        if (sysUser == null) {
            return null;
        }
        BeanUtils.copyProperties(sysUser, loginUser);
        return loginUser;
    }


    /**
     * 通过用户名获取用户角色集合
     *
     * @param username 用户名
     * @return 角色集合
     */
    @Override
    public Set<String> getUserRolesSet(String username) {
        // 查询用户拥有的角色集合
        List<String> roles = sysUserRoleMapper.getRoleByUserName(username);
        log.info("-------通过数据库读取用户拥有的角色Rules------username： " + username + ",Roles size: " + (roles == null ? 0 : roles.size()));
        return new HashSet<>(roles);
    }

    /**
     * 通过用户名获取用户权限集合
     *
     * @param username 用户名
     * @return 权限集合
     */
    @Override
    public Set<String> getUserPermissionsSet(String username) {
        Set<String> permissionSet = new HashSet<>();
        List<SysPermission> permissionList = sysPermissionMapper.queryByUser(username);
        for (SysPermission po : permissionList) {
//			// TODO URL规则有问题？
//			if (oConvertUtils.isNotEmpty(po.getUrl())) {
//				permissionSet.add(po.getUrl());
//			}
            if (oConvertUtils.isNotEmpty(po.getPerms())) {
                permissionSet.add(po.getPerms());
            }
        }
        log.info("-------通过数据库读取用户拥有的权限Perms------username： " + username + ",Perms size: " + (permissionSet == null ? 0 : permissionSet.size()));
        return permissionSet;
    }

    /**
     * 校验用户是否有效
     *
     * @param sysUser
     * @return
     */
    @Override
    public Result<?> checkUserIsEffective(SysUser sysUser) {
        Result<?> result = new Result<Object>();
        //情况1：根据用户信息查询，出于安全性考虑不提示用户不存在，提示用户名或密码错误
        if (sysUser == null) {
            result.error500("用户名或密码错误");
            return result;
        }
        //情况2：根据用户信息查询，该用户已注销
        if (CommonConstant.DEL_FLAG_1.toString().equals(sysUser.getDelFlag())) {
            result.error500("该用户已注销");
            return result;
        }
        //情况3：根据用户信息查询，该用户已冻结
        if (CommonConstant.USER_FREEZE.equals(sysUser.getStatus())) {
            result.error500("该用户已冻结");
            return result;
        }
        return result;
    }

    @Override
    public SysUser getUserByName(String username) {
        return sysUserMapper.getUserByName(username);
        /*QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("del_flag", "0")
                .eq("username", username);
        return sysUserMapper.selectOne(queryWrapper);*/
    }
}
