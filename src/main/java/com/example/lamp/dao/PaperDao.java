package com.example.lamp.dao;

import com.example.lamp.domain.Paper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PaperDao {
    int createpaper(Paper paper); // 주보 생성
    List<Paper> list(); // 주보 목록
    Paper paper(int idx); // 주보 보기
    void save(); // PDF 파일로 저장
}
