package com.example.lamp.service;

import com.example.lamp.dao.BibleDao;
import com.example.lamp.domain.Bible;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BibleServiceImpl implements BibleService{
    private final BibleDao dao;
    @Override
    public List<Bible> searchByRange(String testament, String long_label, int chapter, int first, int last) {
        return dao.searchByRange(testament, long_label, chapter, first, last);
    }
}
