package com.example.lamp.controller;

import com.example.lamp.dao.CcmDao;
import com.example.lamp.domain.Bible;
import com.example.lamp.domain.Paper;
import com.example.lamp.service.BibleService;
import com.example.lamp.service.PaperService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final PaperService paperService;
    private CcmDao dao;

    @GetMapping("/")
    public String Lamp(Model model){
        return "lamp";
    }

    // 주보 목록
    @GetMapping("/page")
    public ModelAndView Page(Model model){
        ModelAndView modelAndView = new ModelAndView("page");
        List<Paper> page = paperService.list();
        modelAndView.addObject("page", page);
        return modelAndView;
    }

    // 주보 보기
    @GetMapping("/paper")
    public String Paper(Model model, @RequestParam(required = false)Integer idx) throws Exception{
        Paper paper = paperService.paper(idx);
        List<Paper> list = paperService.getbible(idx);
        model.addAttribute("paper", paper);
        model.addAttribute("list", list);
        return "paper";
    }
}
