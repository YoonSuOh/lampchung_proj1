package com.example.lamp.service;

import com.example.lamp.dao.MemberDao;
import com.example.lamp.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberDao dao;
    public Member join(Member member) {
        return dao.join(member);
    }
    public Member login(Member member) {
        return dao.login(member);
    }

    public Member findById(Long id) {
        return dao.findById(id);
    }
    public Member findByName(String name){
        return dao.findByName(name);
    }
}
