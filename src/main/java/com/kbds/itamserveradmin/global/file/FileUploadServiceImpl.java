package com.kbds.itamserveradmin.global.file;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileUploadServiceImpl implements FileUploadService{
    @Value("${file.dir}") // application의 properties 변수
    private String uploadPath;

    @Override
    public String saveFile(MultipartFile file) throws IOException {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("File is null or empty");
        }

        String originalName = file.getOriginalFilename();
        assert originalName != null;
        String fileName = originalName.substring(originalName.lastIndexOf("\\") + 1);

        String uuid = UUID.randomUUID().toString();

        String saveName = uploadPath + File.separator + uuid + "_" + fileName;

        Path savePath = Paths.get(saveName);

        try {
            file.transferTo(savePath);
        } catch (IOException e) {
            throw new IOException("Failed to save file", e);
        }

        return saveName;
    }
}
