package com.example.lamp.controller;

import com.example.lamp.domain.Bible;
import com.example.lamp.service.BibleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MainController {

    @GetMapping("/")
    public String lamp(Model model){
        return "/lamp";
    }

    @GetMapping("/paper")
    public String paper(Model model){
        return "/paper";
    }
}
