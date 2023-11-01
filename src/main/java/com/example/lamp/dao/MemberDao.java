package com.example.lamp.dao;

import com.example.lamp.domain.Member;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface MemberDao {
    Member join(Member member); // 회원가입
    Member login(Member member); // 로그인
    Member findById(Long id);
    Member findByName(String name);
}
