package com.example.lamp.dao;

import com.example.lamp.domain.Ccm;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CcmDao {
    List<Ccm> getAllImages();
    void insertImage(Ccm ccm);
    Ccm searchImage(@Param("image")String image);
}
