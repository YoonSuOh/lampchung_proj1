package com.example.lamp.service;

import com.example.lamp.domain.Bible;

public interface BibleService {
    Bible searchByParagraph(String testament, String long_label, int chapter, int paragraph);
}
