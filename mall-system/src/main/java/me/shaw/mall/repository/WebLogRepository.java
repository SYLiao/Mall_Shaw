package me.shaw.mall.repository;

import me.shaw.mall.model.WebLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WebLogRepository extends JpaRepository<WebLog, Long> {
}
