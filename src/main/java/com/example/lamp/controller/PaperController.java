package com.example.lamp.controller;

import com.example.lamp.dao.CcmDao;
import com.example.lamp.dao.PaperDao;
import com.example.lamp.domain.Bible;
import com.example.lamp.domain.Ccm;
import com.example.lamp.domain.Paper;
import com.example.lamp.service.BibleService;
import com.example.lamp.service.PaperService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.io.File;
import java.util.LinkedList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class PaperController {
    private final BibleService bibleService;
    private final PaperService paperService;
    private final CcmDao ccmDao;
    private final ResourceLoader resourceLoader;
    private Ccm savedCcmFile;
    private List<Bible> savedBibleList;
    private List<Ccm> ccmList = new LinkedList<>();
    private static final String UPLOAD_DIR = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "static" + File.separator + "ccm";
    // 주보 생성 페이지로 이동
    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("list", savedBibleList);
        model.addAttribute("ccmList", ccmList);
        return "/create";
    }
    // 교독문 가져오기

    // 성경 구절 가져오기
    @PostMapping("/bible")
    public String range(@RequestParam String testament, @RequestParam String long_label, @RequestParam int chapter, @RequestParam int first, @RequestParam int last, Model model) throws Exception{
        List<Bible> list = bibleService.searchByRange(testament, long_label, chapter, first, last);
        savedBibleList = list;
        model.addAttribute("list", list);
        return "redirect:/create";
    }

    // 찬양 악보 업로드 및 가져오기
    @PostMapping("/upload")
    public String Upload(@RequestParam("files") MultipartFile[] files, Model model) {
        try {
            ccmList.clear();
            for (MultipartFile file : files) {
                String name = file.getOriginalFilename();

                File saveFile = new File(UPLOAD_DIR, name);
                file.transferTo(saveFile);

                Ccm ccm = new Ccm();
                ccm.setImage(name);
                ccm.setPath("/ccm/" + name);
                ccmDao.insertImage(ccm);
                savedCcmFile = ccm;
                ccmList.add(ccm);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/create"; // 주보 생성 페이지로 돌아감
    }

    // 주보 생성
    @PostMapping("/createpaper")
    public String createpaper(Paper paper) throws Exception{
        paperService.createpaper(paper);
        return "redirect:/";
    }
}
