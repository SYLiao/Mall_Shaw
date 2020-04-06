package me.shaw.mall.service;

import me.shaw.mall.model.Permission;
import me.shaw.mall.model.UserAdmin;

import java.util.List;

public interface UserAdminService {

    UserAdmin getUserByName(String username);

    UserAdmin register(String username, String password);

    String login(String username, String password);

    List<Permission> getPermissionList(Long adminId);
}
