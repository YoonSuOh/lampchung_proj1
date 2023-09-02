package com.example.lamp.service;

import com.example.lamp.domain.Paper;

import java.util.List;

public interface PaperService {
    int createpaper(String title, String guidename, String prayname, String respname, String offername, String ccm1, String ccm2, String ccm3, String ccm4, String todaybible, String paragraph, String sentence, String notice, String notice1, String notice2, String notice3, String speachname, String nprayname, String nrespname, String noffername);
    List<Paper> list();
    Paper paper(int idx);
}
