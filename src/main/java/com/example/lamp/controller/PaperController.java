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
import java.util.*;

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
    private List<Ccm> ccmList = new ArrayList<>();
    private String ccms;
    private static final String UPLOAD_DIR = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "static" + File.separator + "ccm";
    // 주보 생성 페이지로 이동
    @GetMapping("/create")
    public String create(Model model){
        ccms = ccmList.toString();
        model.addAttribute("list", savedBibleList);
        for(int i=0;i<ccmList.size();i++){
            System.out.println("출력 결과 : " + ccmList.get(i).getImage());
        }
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
    public String createpaper(HttpServletRequest req, @RequestParam("title") String title, @RequestParam("guidename") String guidename, @RequestParam("prayname") String prayname, @RequestParam("respname") String respname, @RequestParam("offername") String offername, @RequestParam("todaybible") String todaybible, @RequestParam("paragraph") String paragraph, @RequestParam("sentence") String sentence, @RequestParam("Notice") String notice, @RequestParam("Notice1") String notice1, @RequestParam("Notice2") String notice2, @RequestParam("Notice3") String notice3, @RequestParam("speachname") String speachname, @RequestParam("nprayname") String nprayname, @RequestParam("nrespname") String nrespname, @RequestParam("noffername") String noffername) throws Exception{
        log.info("찬양 : " + ccms);
        log.info("리스트 사이즈 : " + ccmList.size());
        String[] ccmlist = new String[4];

        for(int i=0;i<ccmList.size();i++){
            ccmlist[i] = ccmList.get(i).getImage();
        }


        // 분리된 문자열을 각각의 변수에 저장
        String ccm1 = ccmlist[0];
        String ccm2 = ccmlist[1];
        String ccm3 = ccmlist[2];
        String ccm4;
        if(ccmlist[3]!=null){
            ccm4 = ccmlist[3];
        } else {
            ccm4 = null;
        }

        // 결과 출력
        log.info("출력 1 : " + ccm1);
        log.info("출력 2 : " + ccm2);
        log.info("출력 3 : " + ccm3);
        log.info("출력 4 : " + ccm4);

        paperService.createpaper(title, guidename, prayname, respname, offername, ccm1, ccm2, ccm3, ccm4, todaybible, paragraph, sentence, notice, notice1, notice2, notice3, speachname, nprayname, nrespname, noffername);

        return "redirect:/";
    }
}
