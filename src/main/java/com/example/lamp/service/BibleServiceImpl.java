package com.example.lamp.service;

import com.example.lamp.dao.BibleDao;
import com.example.lamp.domain.Bible;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BibleServiceImpl implements BibleService{
    private final BibleDao dao;
    @Override
    public Bible searchByParagraph(String testament, String long_label, int chapter, int paragraph) {
        return dao.searchByParagraph(testament, long_label, chapter, paragraph);
    }
}
