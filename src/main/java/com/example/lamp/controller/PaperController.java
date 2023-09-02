package com.example.lamp.controller;

import com.example.lamp.dao.CcmDao;
import com.example.lamp.dao.PaperDao;
import com.example.lamp.domain.Bible;
import com.example.lamp.domain.Ccm;
import com.example.lamp.domain.Paper;
import com.example.lamp.service.BibleService;
import com.example.lamp.service.PaperService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.File;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class PaperController {
    private final BibleService bibleService;
    private final PaperService paperService;
    private final CcmDao ccmDao;
    private final ResourceLoader resourceLoader;
    private final Logger log = LoggerFactory.getLogger(PaperController.class.getName());
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
    public String createpaper(@RequestParam("title") String title, @RequestParam("guidename") String guidename, @RequestParam("prayname") String prayname, @RequestParam("respname") String respname, @RequestParam("offername") String offername, @RequestParam("todaybible") String todaybible, @RequestParam("paragraph") String paragraph, @RequestParam("sentence") String sentence, @RequestParam("Notice") String notice, @RequestParam("Notice1") String notice1, @RequestParam("Notice2") String notice2, @RequestParam("Notice3") String notice3, @RequestParam("speachname") String speachname, @RequestParam("nprayname") String nprayname, @RequestParam("nrespname") String nrespname, @RequestParam("noffername") String noffername) throws Exception{
        String ccm="";
        Iterator<Ccm> it = ccmList.iterator();
        while(it.hasNext()){
            ccm = ccm + it.next() + "";
        }
        log.info(ccm);
        String[] parts = ccm.split(",");

        for(int i=0;i<=3;i++){
            log.info(parts[i]);
        }

        String ccm1 = parts[0];
        String ccm2 = parts[1];
        String ccm3 = parts[2];
        String ccm4 = parts[3];

        paperService.createpaper(title, guidename, prayname, respname, offername, ccm1, ccm2, ccm3, ccm4, todaybible, paragraph, sentence, notice, notice1, notice2, notice3, speachname, nprayname, nrespname, noffername);

        return "redirect:/";
    }
}
