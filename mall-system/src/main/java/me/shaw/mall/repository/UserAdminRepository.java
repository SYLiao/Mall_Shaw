package me.shaw.mall.repository;

import me.shaw.mall.model.UserAdmin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserAdminRepository extends JpaRepository<UserAdmin, Long> {

    List<UserAdmin> findByUsername(String username);
}
