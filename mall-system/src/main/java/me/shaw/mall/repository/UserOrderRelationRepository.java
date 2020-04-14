package me.shaw.mall.repository;

import me.shaw.mall.model.UserOrderRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserOrderRelationRepository extends JpaRepository<UserOrderRelation, Long> {

    @Modifying
    @Query(value = "delete from user_order_relation where order_id = ?l", nativeQuery = true)
    void deleteByOrderId(Long orderId);
}
