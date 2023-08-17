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
    public int createpaper(Paper paper) {
        return dao.createpaper(paper);
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
