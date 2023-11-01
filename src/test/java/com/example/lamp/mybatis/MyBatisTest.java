package com.example.lamp.mybatis;

import com.example.lamp.controller.PaperController;
import com.example.lamp.dao.TestDao;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.util.List;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MyBatisTest {
    @Autowired
    private TestDao dao;
    private final Logger log = LoggerFactory.getLogger(PaperController.class.getName());

    @Test
    @DisplayName("MyBatis 연동 성공")
    void getTest(){
        // given
        String name = "TEST";

        //when
        List<com.example.lamp.domain.Test> list = dao.getTestData();

        // then
        for(com.example.lamp.domain.Test test : list){
            System.out.println(test);
        }
    }
}
