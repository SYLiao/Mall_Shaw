package me.shaw.mall.repository;

import me.shaw.mall.model.UserRoleRelation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRoleRelation, Long> {
}
