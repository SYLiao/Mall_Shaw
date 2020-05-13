package me.shaw.mall.service;

import me.shaw.mall.mongodb.document.MemberReadHistory;

import java.util.List;

public interface MemberHistoryService {

    void createMemberHistory(MemberReadHistory memberReadHistory);

    void deleteById(Long id);

    List<MemberReadHistory> listAll(Long Id);
}
