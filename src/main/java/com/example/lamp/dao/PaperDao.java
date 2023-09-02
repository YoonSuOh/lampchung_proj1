package com.example.lamp.dao;

import com.example.lamp.domain.Paper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PaperDao {
    int createpaper(String title, String guidename, String prayname, String respname, String offername, String ccm1, String ccm2, String ccm3, String ccm4, String todaybible, String paragraph, String sentence, String notice, String notice1, String notice2, String notice3, String speachname, String nprayname, String nrespname, String noffername); // 주보 생성
    List<Paper> list(); // 주보 목록
    Paper paper(int idx); // 주보 보기
    void save(); // PDF 파일로 저장
}
