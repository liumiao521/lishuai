package com.lishuai.spring.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lishuai.spring.pojo.LoginUser;
import com.lishuai.spring.pojo.SysUser;
import com.lishuai.spring.utils.Result;

import java.util.Set;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @Author scott
 * @since 2018-12-20
 */
public interface ISysUserService extends IService<SysUser> {

    /**
     * 根据用户账号查询用户信息
     *
     * @param username
     * @return
     */
    LoginUser getUserByName(String username,String temp);

    SysUser getUserByName(String username);

    /**
     * 通过用户名获取用户角色集合
     *
     * @param username 用户名
     * @return 角色集合
     */
    Set<String> getUserRolesSet(String username);

    /**
     * 通过用户名获取用户权限集合
     *
     * @param username 用户名
     * @return 权限集合
     */
    Set<String> getUserPermissionsSet(String username);

    /**
     * 校验用户是否有效
     * @param sysUser
     * @return
     */
    Result checkUserIsEffective(SysUser sysUser);
}
