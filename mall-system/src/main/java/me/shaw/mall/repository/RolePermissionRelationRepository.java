package me.shaw.mall.repository;

import me.shaw.mall.model.RolePermissionRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolePermissionRelationRepository extends JpaRepository<RolePermissionRelation, Long> {
}
