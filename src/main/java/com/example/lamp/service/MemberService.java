package com.example.lamp.service;

import com.example.lamp.domain.Member;

public interface MemberService {
    void join(Member member); // 회원가입
    Member login(Member member); // 로그인
}
