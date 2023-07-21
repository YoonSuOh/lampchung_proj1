package com.example.lamp.controller;

import com.example.lamp.domain.Bible;
import com.example.lamp.service.BibleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
    private final BibleService bibleService;

    public MainController(BibleService bibleService) {
        this.bibleService = bibleService;
    }

    @GetMapping("/")
    public String lamp(Model model){
        return "/lamp";
    }

    @PostMapping ("/")
    public String paragraph(@RequestParam String testament, @RequestParam String long_label, @RequestParam int chapter, @RequestParam int paragraph, Model model) throws Exception{
        Bible bible = bibleService.searchByParagraph(testament, long_label, chapter, paragraph);
        model.addAttribute("bible", bible);
        return "/lamp";
    }
}
