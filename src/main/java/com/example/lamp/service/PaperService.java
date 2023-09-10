package com.example.lamp.service;

import com.example.lamp.domain.Paper;

import java.util.List;

public interface PaperService {
    int createpaper(String title, String guidename, String prayname, String respname, String offername, String ccm1, String ccm2, String ccm3, String ccm4, String resp, String long_label, int chapter, int start, int fin, String notice, String notice1, String notice2, String notice3, String speachname, String nprayname, String nrespname, String noffername);
    List<Paper> list();
    Paper paper(int idx);
    List<Paper> getbible(int idx); // 주보 목록 보여질 때 성경구절 가져오기
}
