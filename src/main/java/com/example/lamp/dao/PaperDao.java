package com.example.lamp.dao;

import com.example.lamp.domain.Paper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PaperDao {
    int createpaper(@Param("title")String title, @Param("guidename")String guidename, @Param("prayname")String prayname, @Param("respname")String respname, @Param("offername")String offername,  @Param("ccm1")String ccm1,  @Param("ccm2")String ccm2,  @Param("ccm3")String ccm3,  @Param("ccm4")String ccm4, @Param("todaybible")String todaybible, @Param("paragraph")String paragraph, @Param("sentence")String sentence, @Param("notice")String notice, @Param("notice1")String notice1, @Param("notice2")String notice2, @Param("notice3")String notice3, @Param("speachname")String speachname, @Param("nprayname")String nprayname, @Param("nrespname")String nrespname, @Param("noffername")String noffername); // 주보 생성
    List<Paper> list(); // 주보 목록
    Paper paper(int idx); // 주보 보기
    void save(); // PDF 파일로 저장
}
