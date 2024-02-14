package com.kbds.itamserveradmin.global.file;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@RestController
@Log4j2
public class FileController {
    @Autowired
    private FileUploadService fileUploadService;

    @Value("${file.dir}") // application 의 properties 의 변수
    private String uploadPath;


    @PostMapping("/upload")
    public ResponseEntity<List<UploadResultDTO>> upload (@RequestParam("files") MultipartFile[] files) {
        List<UploadResultDTO> resultDTOList = new ArrayList<>();
        if (files == null || files.length == 0) {
            return ResponseEntity.badRequest().build();
        }

        for (MultipartFile file : files) {
            if (file.isEmpty()) {
                continue;
            }

            try {
                String filePath = fileUploadService.saveFile(file); // 파일 업로드 후 경로 반환
                resultDTOList.add(new UploadResultDTO(file.getOriginalFilename(), filePath)); // 파일 이름과 경로 추가
            } catch (IOException e) {
                log.error("Failed to upload file", e);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 업로드 실패 시 500 에러 반환
            }
        }

        return ResponseEntity.ok(resultDTOList);
    }
}
