package me.shaw.mall.repository;

import me.shaw.mall.model.Permission;
import me.shaw.mall.model.UserPermissionRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserPermissionRepository extends JpaRepository<UserPermissionRelation, Long> {

    List<UserPermissionRelation> findByAdminId(Long adminId);
}
