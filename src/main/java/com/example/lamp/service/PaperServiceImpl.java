package com.example.lamp.service;

import com.example.lamp.dao.PaperDao;
import com.example.lamp.domain.Paper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaperServiceImpl implements PaperService{
    private final PaperDao dao;
    @Override
    public int createpaper(String title, String guidename, String prayname, String respname, String offername, String ccm1, String ccm2, String ccm3, String ccm4, String todaybible, String paragraph, String sentence, String notice, String notice1, String notice2, String notice3, String speachname, String nprayname, String nrespname, String noffername) {
        return dao.createpaper(title, guidename, prayname, respname, offername, ccm1, ccm2, ccm3, ccm4, todaybible, paragraph, sentence, notice, notice1, notice2, notice3, speachname, nprayname, nrespname, noffername);
    }

    @Override
    public List<Paper> list() {
        return dao.list();
    }

    @Override
    public Paper paper(int idx) {
        return dao.paper(idx);
    }
}
