package com.kbds.itamserveradmin.global.file;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileUploadService {
    String saveFile(MultipartFile file) throws IOException;
}
