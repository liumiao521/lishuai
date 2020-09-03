package com.lishuai.spring.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lishuai.spring.pojo.SysPermission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 菜单权限表 Mapper 接口
 * </p>
 *
 * @Author scott
 * @since 2018-12-21
 */
public interface SysPermissionMapper extends BaseMapper<SysPermission> {
    /**
     * 根据用户查询用户权限
     */
    List<SysPermission> queryByUser(@Param("username") String username);
}
