package me.shaw.mall.dao;

import me.shaw.mall.model.Permission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminRoleRelationDAO {

    List<Permission> getPermissionList(@Param("adminId") Long adminId);
}
