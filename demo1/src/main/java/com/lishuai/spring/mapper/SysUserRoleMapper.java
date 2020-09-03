package com.lishuai.spring.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lishuai.spring.pojo.SysUserRole;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * <p>
 * 用户角色表 Mapper 接口
 * </p>
 *
 * @Author scott
 * @since 2018-12-21
 */
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {

	@Select("select role_code from sys_role where id in (select role_id from sys_user_role where user_id = (select id from sys_user where username=#{username}))")
	List<String> getRoleByUserName(@Param("username") String username);

	@Select("select role_code from sys_role where id in (select role_id from sys_user_role where user_id = #{userId})")
	List<String> getRoleByUserId(@Param("userId") String userId);

	@Select("select id from sys_role where id in (select role_id from sys_user_role where user_id = (select id from sys_user where username=#{username}))")
	List<String> getRoleIdByUserName(@Param("username") String username);

	void setDeleteByUserIds(@Param("userIds") List<String> userIds);

	@Update("delete from sys_user_role where user_id =#{userId}")
	void setDeleteByUserId(@Param("userId") String userId);

	void dynamicDeleteSql(String sql);
}
