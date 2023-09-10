package com.example.lamp.controller;

import com.example.lamp.dao.CcmDao;
import com.example.lamp.domain.Bible;
import com.example.lamp.domain.Ccm;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import com.example.lamp.service.BibleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@Controller
public class PaperController {
    private BibleService bibleService;
    private CcmDao dao;
    private final ResourceLoader resourceLoader;
    private Ccm savedCcmFile;
    private List<Bible> savedBibleList;
    private List<Ccm> ccmList = new LinkedList<>();
    private static final String UPLOAD_DIR = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "static" + File.separator + "ccm";
  // CcmDao의 의존성 주입
    public PaperController(BibleService bibleService, CcmDao dao, ResourceLoader resourceLoader) {
        this.dao = dao;
        this.bibleService = bibleService;
        this.resourceLoader = resourceLoader;
    }

    // PDF 파일로 저장
    @RequestMapping("/{fileName}")
    public ResponseEntity<Resource> resourceFileDownload(@PathVariable String fileName) {
        try {
            Resource resource = resourceLoader.getResource("file:/C:/Devroot/temp/" + fileName);
            File file = resource.getFile();

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"")
                    .header(HttpHeaders.CONTENT_LENGTH, String.valueOf(file.length()))
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM.toString())
                    .body(resource);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(null);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    // 주보 생성 페이지로 이동
    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("list", savedBibleList);
        model.addAttribute("ccmList", ccmList);
        return "/create";
    }

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
                dao.insertImage(ccm);
                savedCcmFile = ccm;
                ccmList.add(ccm);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/create"; // 주보 생성 페이지로 돌아감
    }
}
