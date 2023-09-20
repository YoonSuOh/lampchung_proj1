package com.example.lamp.dao;

import com.example.lamp.domain.Member;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberDao {
    void join(Member member); // 회원가입
    Member login(Member member); // 로그인
}
