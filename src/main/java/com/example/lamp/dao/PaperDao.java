package com.example.lamp.dao;

import com.example.lamp.domain.Paper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface PaperDao {
    int createpaper(@Param("title")String title, @Param("guidename")String guidename, @Param("prayname")String prayname, @Param("respname")String respname, @Param("offername")String offername,  @Param("ccm1")String ccm1,  @Param("ccm2")String ccm2,  @Param("ccm3")String ccm3,  @Param("ccm4")String ccm4, @Param("resp") String resp, @Param("long_label")String long_label, @Param("chapter")int chapter, @Param("start")int start, @Param("fin")int fin, @Param("notice")String notice, @Param("notice1")String notice1, @Param("notice2")String notice2, @Param("notice3")String notice3, @Param("speachname")String speachname, @Param("nprayname")String nprayname, @Param("nrespname")String nrespname, @Param("noffername")String noffername); // 주보 생성
    List<Paper> selectList(Map<String, Object> paramMap); // 주보 목록
    Paper paper(int idx); // 주보 보기
    List<Paper> getbible(int idx); // 주보 목록 보여질 때 성경구절 가져오기
    void save(); // PDF 파일로 저장
    public int count(); // 주보 총 갯수
}
