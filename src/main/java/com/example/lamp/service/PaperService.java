package com.example.lamp.service;

import com.example.lamp.domain.Paper;

import java.util.List;

public interface PaperService {
    int createpaper(Paper paper);
    List<Paper> list();
    Paper paper(int idx);
}
