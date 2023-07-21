package com.example.lamp.dao;

import com.example.lamp.domain.Bible;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BibleDao {
    Bible searchByParagraph(String testament, String long_label, int chapter, int paragraph);
}
