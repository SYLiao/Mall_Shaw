package me.shaw.mall.mongodb.repository;

import me.shaw.mall.mongodb.document.MemberReadHistory;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MemberReadHistoryRepository extends MongoRepository<MemberReadHistory, Long> {

    List<MemberReadHistory> findByMemberIdOrderByCreateTimeDesc(Long MemberId);
}
