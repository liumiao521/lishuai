package com.lishuai.spring.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lishuai.spring.pojo.SysRole;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @Author scott
 * @since 2018-12-19
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {

    /**
     * @Author scott
     * @Date 2019/12/13 16:12
     * @Description: 删除角色与用户关系
     */
    @Delete("delete from sys_user_role where role_id = #{roleId}")
    void deleteRoleUserRelation(@Param("roleId") String roleId);

    @Select("select * from sys_role where role_name=#{roleName} limit 1")
    SysRole getSysRoleByRoleName(@Param("roleName") String roleName);

    @Select("select id, role_name from sys_role where id in (select role_id from sys_user_role where user_id = #{userId})")
    List<SysRole> getRoleByUserId(@Param("userId") String userId);

}
