package me.shaw.mall.repository;

import me.shaw.mall.model.UserOrderRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserOrderRelationRepository extends JpaRepository<UserOrderRelation, Long> {

    void deleteByOrderId(Long orderId);
}
