package com.example.lamp.service;

import com.example.lamp.domain.Bible;

import java.util.List;

public interface BibleService {
    List<Bible> searchByRange(String testament, String long_label, int chapter, int first, int last);
}
