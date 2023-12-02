package com.example.lamp.common;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class FileService {
    // 실제 업로드가 된 이미지가 저장될 경로    (서버)
    public static final String FILE_UPLOAD_PATH = "D:\\lamp_proj1\\src\\main\\resources\\static\\ccm/"; // 윈도우
    //  public static final String FILE_UPLOAD_PATH = "/Users/suohyoon/lamp_chung1/src/main/resources/static/ccm/"; // 맥


    // input: userLoginId, file(이미지) output: web imagePath
    public String saveFile(MultipartFile file) {
        // 폴더 생성
        // 예: aaaa_178945646/sun.png
        String directoryName = String.valueOf(System.currentTimeMillis());
        String filePath = FILE_UPLOAD_PATH + directoryName; // D:\\신보람\\5_spring_project\\memo\\workspace\\images/aaaa_178945646

        File directory = new File(filePath);
        if (!directory.mkdir()) {
            // 폴더 생성 실패 시 이미지 경로 null로 리턴
            return null;
        }

        // 파일 업로드: byte 단위로 업로드
        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(filePath + "/" + file.getOriginalFilename()); // 디렉토리 경로 + 사용자가 올린 파일명
            Files.write(path, bytes); // 파일 업로드
        } catch (IOException e) {
            e.printStackTrace();
            return null; // 이미지 업로드 실패 시 null 리턴
        }

        // 파일 업로드가 성공했으면 웹 이미지 url path를 리턴
        return directoryName + "/" + file.getOriginalFilename();
    }
}
