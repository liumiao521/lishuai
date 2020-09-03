package com.lishuai.spring.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lishuai.spring.pojo.SysUser;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @Author scott
 * @since 2018-12-20
 */
public interface SysUserMapper extends BaseMapper<SysUser> {
    /**
     * 通过用户账号查询用户信息
     *
     * @param username
     * @return
     */
    SysUser getUserByName(@Param("username") String username);


    /**
     * 根据角色Id查询用户信息
     *
     * @param page
     * @param
     * @return
     */
    IPage<SysUser> getUserByRoleId(Page page, @Param("roleId") String roleId, @Param("username") String username, @Param("realname") String realname, @Param("orgCode") String orgCode);


    /**
     * 根据手机号查询用户信息
     *
     * @param phone
     * @return
     */
    public SysUser getUserByPhone(@Param("phone") String phone);

}
