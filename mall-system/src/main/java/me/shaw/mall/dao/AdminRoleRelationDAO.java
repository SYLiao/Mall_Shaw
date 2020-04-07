package me.shaw.mall.dao;

import me.shaw.mall.model.Permission;
import me.shaw.mall.model.UserPermissionRelation;
import me.shaw.mall.repository.PermissionRepository;
import me.shaw.mall.repository.UserPermissionRepository;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AdminRoleRelationDAO {

    @Autowired
    private UserPermissionRepository userPermissionRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    public List<Permission> getPermissionList(Long adminId){
        List<UserPermissionRelation> result = userPermissionRepository.findByAdminId(adminId);
        List<Permission> permissions = new ArrayList<>();
        for(UserPermissionRelation userPermissionRelation : result){
            Permission permission = permissionRepository.findById(userPermissionRelation.getPermissionId()).orElse(null);
            if(permission == null) continue;
            permissions.add(permission);
        }
        return permissions;
    }
}
