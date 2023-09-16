package com.example.lamp.service;

import com.example.lamp.dao.MemberDao;
import com.example.lamp.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberDao dao;
    @Override
    public void join(Member member) {
        dao.join(member);
    }

    @Override
    public Member login(Member member) {
        return dao.login(member);
    }
}
