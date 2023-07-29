package com.example.lamp.dao;

import com.example.lamp.domain.Bible;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BibleDao {
    List<Bible> searchByRange(@Param("testament")String testament, @Param("long_label")String long_label, @Param("chapter")int chapter, @Param("first")int first, @Param("last")int last);
}
