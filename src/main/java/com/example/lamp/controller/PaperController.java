package com.example.lamp.controller;

import com.example.lamp.dao.CcmDao;
import com.example.lamp.domain.Bible;
import com.example.lamp.domain.Ccm;
import com.example.lamp.entity.VersicleEntity;
import com.example.lamp.repository.VersicleRepository;
import com.example.lamp.service.BibleService;
import com.example.lamp.service.PaperService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.*;

@Controller
@RequiredArgsConstructor
public class PaperController {
    @Autowired
    private final BibleService bibleService;
    @Autowired
    private final PaperService paperService;
    @Autowired
    private final CcmDao ccmDao;
    @Autowired
    private final VersicleRepository versicleRepository;

    private final ResourceLoader resourceLoader;
    private final Logger log = LoggerFactory.getLogger(PaperController.class.getName());
    private Ccm savedCcmFile;
    private List<Bible> savedBibleList;
    private List<VersicleEntity> savedVersicleList;
    private List<Ccm> ccmList = new ArrayList<>();
    private String savetitle, saveguidename, saveprayname, saverespname, saveoffername, savelonglabel, savenotice, savenotice1, savenotice2, savenotice3, savespeachname, savenprayname, savenrespname, savenoffername;
    private int saveresp, chapter, start, fin;
   // 주보 생성 페이지로 이동
    static String htmlFileReader(String pathStr){

        ClassPathResource resource = new ClassPathResource(pathStr);
        String resultcontent = "";
        try {
            InputStream inputStream = resource.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));

            StringBuilder builder = new StringBuilder();
            while (true) {
                String line = br.readLine();
                if(line == null ) break;
                builder.append(line);
            }

            resultcontent = builder.toString();

        } catch (IOException e) {

        }
        return resultcontent;
    }
    private static final String UPLOAD_DIR=htmlFileReader("static/ccm");
    @GetMapping("/create")
    public String create(Model model){
        // System.out.println("savedVersicleList = " + savedVersicleList.get(3).getSentence());
        model.addAttribute("saveresp", saveresp);
        model.addAttribute("list", savedBibleList);
        model.addAttribute("versicle", savedVersicleList);
        model.addAttribute("ccmList", ccmList);
        model.addAttribute("savetitle", savetitle);
        model.addAttribute("saveguidename", saveguidename);
        model.addAttribute("saveprayname", saveprayname);
        model.addAttribute("saverespname", saverespname);
        model.addAttribute("saveoffername", saveoffername);
        model.addAttribute("savenotice", savenotice);
        model.addAttribute("savenotice1", savenotice1);
        model.addAttribute("savenotice2", savenotice2);
        model.addAttribute("savenotice3", savenotice3);
        model.addAttribute("savespeachname", savespeachname);
        model.addAttribute("savenprayname", savenprayname);
        model.addAttribute("savenrespname", savenrespname);
        model.addAttribute("savenoffername", savenoffername);
        return "create";
    }

    // 교독문 가져오기
    @PostMapping("/versicle")
    public String getVersicle(
            @RequestParam(value = "title", required = false, defaultValue = "null") String title,
            @RequestParam(value = "guidename", required = false, defaultValue="null") String guidename,
            @RequestParam(value = "prayname", required = false, defaultValue = "null") String prayname,
            @RequestParam(value="respname", required = false, defaultValue = "null") String respname,
            @RequestParam(value="offername", required = false, defaultValue = "null") String offername,
            @RequestParam(value="resp", required = false, defaultValue = "null") int resp,
            @RequestParam(value="Notice", required = false, defaultValue = "null") String notice ,
            @RequestParam(value="Notice1", required = false, defaultValue = "null") String notice1,
            @RequestParam(value="Notice2", required = false, defaultValue = "null") String notice2,
            @RequestParam(value="Notice3", required = false, defaultValue = "null") String notice3,
            @RequestParam(value="speachname", required = false, defaultValue = "null") String speachname,
            @RequestParam(value="nprayname", required = false, defaultValue = "null") String nprayname,
            @RequestParam(value="nrespname", required = false, defaultValue = "null") String nrespname,
            @RequestParam(value="noffername", required = false, defaultValue = "null") String noffername, Model model) throws Exception{
        List<VersicleEntity> list = versicleRepository.findByParagraph(resp);

        savedVersicleList = list;
        model.addAttribute("list", list);
        savetitle=title;
        saveguidename=guidename;
        saveprayname=prayname;
        saverespname=respname;
        saveoffername=offername;
        saveresp=resp;
        savenotice=notice;
        savenotice1=notice1;
        savenotice2=notice2;
        savenotice3=notice3;
        savespeachname=speachname;
        savenprayname=nprayname;
        savenrespname=nrespname;
        savenoffername=noffername;
        return "redirect:/create/#Response";
    }
    // 성경 구절 가져오기
    @PostMapping("/bible")
    public String range(
            @RequestParam String testament,
            @RequestParam String long_label,
            @RequestParam int chapters,
            @RequestParam int first,
            @RequestParam int last,
            @RequestParam(value = "title", required = false, defaultValue = "null") String title,
            @RequestParam(value = "guidename", required = false, defaultValue="null") String guidename,
            @RequestParam(value = "prayname", required = false, defaultValue = "null") String prayname,
            @RequestParam(value="respname", required = false, defaultValue = "null") String respname,
            @RequestParam(value="offername", required = false, defaultValue = "null") String offername,
            @RequestParam(value="Notice", required = false, defaultValue = "null") String notice ,
            @RequestParam(value="Notice1", required = false, defaultValue = "null") String notice1,
            @RequestParam(value="Notice2", required = false, defaultValue = "null") String notice2,
            @RequestParam(value="Notice3", required = false, defaultValue = "null") String notice3,
            @RequestParam(value="speachname", required = false, defaultValue = "null") String speachname,
            @RequestParam(value="nprayname", required = false, defaultValue = "null") String nprayname,
            @RequestParam(value="nrespname", required = false, defaultValue = "null") String nrespname,
            @RequestParam(value="noffername", required = false, defaultValue = "null") String noffername, Model model) throws Exception{
        List<Bible> list = bibleService.searchByRange(testament, long_label, chapters, first, last);
        savedBibleList = list;
        model.addAttribute("list", list);
        savetitle=title;
        saveguidename=guidename;
        saveprayname=prayname;
        saverespname=respname;
        saveoffername=offername;
        savenotice=notice;
        savenotice1=notice1;
        savenotice2=notice2;
        savenotice3=notice3;
        savespeachname=speachname;
        savenprayname=nprayname;
        savenrespname=nrespname;
        savenoffername=noffername;

        log.info("출력 6  : " + saveresp);
        log.info("출력 6  : " + chapter);
        log.info("출력 7 : " + start);
        log.info("출력 8 : " + fin);
        return "redirect:/create/#Read";
    }

    // 찬양 악보 업로드 및 가져오기
    @PostMapping("/upload")
    public String Upload(
            @RequestParam("files") MultipartFile[] files,
            @RequestParam(value = "title", required = false, defaultValue = "null") String title,
            @RequestParam(value = "guidename", required = false, defaultValue="null") String guidename,
            @RequestParam(value = "prayname", required = false, defaultValue = "null") String prayname,
            @RequestParam(value="respname", required = false, defaultValue = "null") String respname,
            @RequestParam(value="offername", required = false, defaultValue = "null") String offername,
            @RequestParam(value="Notice", required = false, defaultValue = "null") String notice,
            @RequestParam(value="Notice1", required = false, defaultValue = "null") String notice1,
            @RequestParam(value="Notice2", required = false, defaultValue = "null") String notice2,
            @RequestParam(value="Notice3", required = false, defaultValue = "null") String notice3,
            @RequestParam(value="speachname", required = false, defaultValue = "null") String speachname,
            @RequestParam(value="nprayname", required = false, defaultValue = "null") String nprayname,
            @RequestParam(value="nrespname", required = false, defaultValue = "null") String nrespname,
            @RequestParam(value="noffername", required = false, defaultValue = "null") String noffername, Model model) {
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
        savetitle=title;
        saveguidename=guidename;
        saveprayname=prayname;
        saverespname=respname;
        saveoffername=offername;
        savenotice=notice;
        savenotice1=notice1;
        savenotice2=notice2;
        savenotice3=notice3;
        savespeachname=speachname;
        savenprayname=nprayname;
        savenrespname=nrespname;
        savenoffername=noffername;
        return "redirect:/create/#Song2"; // 주보 생성 페이지로 돌아감
    }

    // 주보 생성
    @PostMapping("/createpaper")
    public String createpaper(HttpServletRequest req, @RequestParam("title") String title, @RequestParam("guidename") String guidename, @RequestParam("prayname") String prayname, @RequestParam("respname") String respname, @RequestParam("offername") String offername, @RequestParam("long_label") String long_label, @RequestParam("Notice") String notice, @RequestParam("Notice1") String notice1, @RequestParam("Notice2") String notice2, @RequestParam("Notice3") String notice3, @RequestParam("speachname") String speachname, @RequestParam("nprayname") String nprayname, @RequestParam("nrespname") String nrespname, @RequestParam("noffername") String noffername) throws Exception{
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
        log.info("출력 5 : " + long_label);
        log.info("출력 6  : " + chapter);
        log.info("출력 7 : " + start);
        log.info("출력 8 : " + fin);
        log.info("출력 9 : " + saveresp);

        paperService.createpaper(title, guidename, prayname, respname, offername, ccm1, ccm2, ccm3, ccm4, saveresp, savelonglabel, chapter, start, fin, notice, notice1, notice2, notice3, speachname, nprayname, nrespname, noffername);

        return "redirect:/";
    }

}