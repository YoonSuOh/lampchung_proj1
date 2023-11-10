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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.PrintWriter;
import java.util.*;

/**
 *
 */
@RequiredArgsConstructor
@Controller
public class PaperRestController {
    /* 전역 변수, 생성자 */
    // Spring Bean DI
    @Autowired
    private final BibleService bibleService;
    @Autowired
    private final PaperService paperService;
    @Autowired
    private final CcmDao ccmDao;
    @Autowired
    private final VersicleRepository versicleRepository;

    // 전역 상수 / 변수
    private final Logger log = LoggerFactory.getLogger(PaperRestController.class.getName());
    private List<VersicleEntity> versicle;

    /* View */
    // 주보 생성 페이지 이동
    @GetMapping("/create")
    public String getCreatePaper(){
        return "create";
    }

    /* API */
    /** 교독문 가져오기 API
     * @param resp
     * @param model
     * @return
     * @throws Exception
     */
    @ResponseBody
    @PostMapping("/versicle")
    public Map<String, Object> getVersicle(
            @RequestParam(value="resp", required = false, defaultValue = "null") int resp, Model model) throws Exception{
        List<VersicleEntity> versicle = versicleRepository.findByParagraph(resp);

        Map<String, Object> result = new HashMap<>();

        log.info(versicle.get(3).getSentence());

        result.put("code", 200);
        result.put("result", versicle);

        return result;
    }

    /** 성경 구절 가져오기 API
     * @param testament
     * @param long_label
     * @param chapters
     * @param first
     * @param last
     * @return
     * @throws Exception
     */
    @ResponseBody
    @PostMapping("/bible")
    public Map<String, Object> getBible(
            @RequestParam("testament") String testament,
            @RequestParam("long_label") String long_label,
            @RequestParam("chapters") int chapters,
            @RequestParam("first") int first,
            @RequestParam("last") int last) throws Exception{

        List<Bible> list = bibleService.searchByRange(testament, long_label, chapters, first, last);

        Map<String, Object> result = new HashMap<>();

        log.info(list.get(1).getSentence());

        result.put("code", 200);
        result.put("result", list);

        return result;
    }


    /** 주보 생성 API
     * @param title
     *
     * @param guidename
     * @param prayname
     * @param respname
     * @param offername
     * @param resp
     * @param long_label
     * @param chapter
     * @param first
     * @param last
     * @param notice
     * @param notice1
     * @param notice2
     * @param notice3
     * @param speachname
     * @param nprayname
     * @param nrespname
     * @param noffername
     * @param files
     * @return
     */
    @PostMapping("/create")
    @ResponseBody
    public Map<String, Object> createPaper(
            @RequestParam("title") String title,
            @RequestParam("guidename") String guidename,
            @RequestParam("prayname") String prayname,
            @RequestParam("respname") String respname,
            @RequestParam("respname") String offername,
            @RequestParam("resp") int resp,
            @RequestParam("long_label") String long_label,
            @RequestParam("chapters") int chapter,
            @RequestParam("first") int first,
            @RequestParam("last") int last,
            @RequestParam("Notice") String notice ,
            @RequestParam("Notice1") String notice1,
            @RequestParam("Notice2") String notice2,
            @RequestParam("Notice3") String notice3,
            @RequestParam("speachname") String speachname,
            @RequestParam("nprayname") String nprayname,
            @RequestParam("nrespname") String nrespname,
            @RequestParam("noffername") String noffername,
            @RequestParam("file") List<MultipartFile> files
    ){

        title = title.replaceAll(",", "");
        guidename = guidename.replaceAll(",undefined", "");
        prayname = prayname.replaceAll(",undefined", "");
        respname = respname.replaceAll(",undefined", "");
        offername = offername.replaceAll(",undefined", "");
        long_label = long_label.replaceAll(",", "");
        notice = notice.replaceAll(",", "");
        notice1 = notice1.replaceAll(",", "");
        notice2 = notice2.replaceAll(",", "");
        notice3 = notice3.replaceAll(",", "");
        speachname = speachname.replaceAll(",undefined", "");
        nprayname = nprayname.replaceAll(",", "");
        nrespname = nrespname.replaceAll(",", "");
        noffername = noffername.replaceAll(",", "");

        log.info("제목: " + title);
        log.info("인도: " +guidename);
        log.info("기도: " +prayname);
        log.info("교독: " +respname);
        log.info("헌금: " +offername);
        log.info("교독문: " + resp);
        log.info("성경전서: " +long_label);
        log.info("장: " + chapter);
        log.info("시작하는 절: " + first);
        log.info("끝나는 절: " + last);
        log.info("전체 공지: "+notice);
        log.info("1부: "+notice1);
        log.info("2부: "+notice2);
        log.info("3부: "+notice3);
        log.info("설교: " +speachname);
        log.info("다음주 기도: " +nprayname);
        log.info("다음주 교독: " +nrespname);
        log.info("다음주 헌금: " +noffername);
        log.info("첫번째 파일: " + files);

        List<String> ccms = paperService.fileupload(files);
        int arrListSize = ccms.size();

        String ccmList[] = ccms.toArray(new String[arrListSize]);

        log.info("리스트 길이 : " + arrListSize);

        for(int i=0;i<ccmList.length;i++){
            ccmList[i] = ccms.get(i);
        }

        String ccm1 = ccmList[0];
        String ccm2 = ccmList[1];
        String ccm3 = ccmList[2];
        String ccm4;
        if(arrListSize == 4){
            ccm4 = ccmList[3];
        } else {
            ccm4 = null;
        }

        log.info("첫번째 ccm : " + ccm1);
        log.info("두번째 ccm : " + ccm2);
        log.info("세번째 ccm : " + ccm3);
        log.info("네번째 ccm : " + ccm4);

        paperService.createpaper(title, guidename, prayname, respname, offername, ccm1, ccm2, ccm3, ccm4, resp, long_label, chapter, first, last, notice, notice1, notice2, notice3, speachname, nprayname, nrespname, noffername);

        Map<String, Object> result = new HashMap<>();

        result.put("code", 200);
        result.put("result", "성공");

        return result;
                    
    }
}
