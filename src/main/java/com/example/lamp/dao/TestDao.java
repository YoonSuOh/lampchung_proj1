package com.example.lamp.dao;

import com.example.lamp.domain.Test;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface TestDao {
    List<Test> getTestData();
}
