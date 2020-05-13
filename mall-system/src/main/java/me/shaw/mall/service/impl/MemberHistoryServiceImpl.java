package me.shaw.mall.service.impl;

import com.google.common.collect.Lists;
import com.sun.xml.internal.xsom.XSListSimpleType;
import lombok.var;
import me.shaw.mall.mongodb.document.MemberReadHistory;
import me.shaw.mall.mongodb.repository.MemberReadHistoryRepository;
import me.shaw.mall.service.MemberHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MemberHistoryServiceImpl implements MemberHistoryService {

    @Autowired
    private MemberReadHistoryRepository memberReadHistoryRepository;

    @Override
    public void createMemberHistory(MemberReadHistory memberReadHistory) {
        memberReadHistory.setId(null);
        memberReadHistory.setCreateTime(new Date());
        memberReadHistoryRepository.save(memberReadHistory);
    }

    @Override
    public void deleteById(Long id) {
        memberReadHistoryRepository.deleteById(id);
    }

    @Override
    public List<MemberReadHistory> listAll(Long memberId) {
        Iterable<MemberReadHistory> result = memberReadHistoryRepository.findByMemberIdOrderByCreateTimeDesc(memberId);
        List<MemberReadHistory> list = Lists.newArrayList();
        result.forEach(element -> list.add((element)));
        return list;
    }
}
