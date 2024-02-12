package com.kbds.itamserveradmin.global.file;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Data
@AllArgsConstructor
public class UploadResultDTO implements Serializable {

    private String fileName;
    private String uuid;

    public String getFileURL() {
        return URLEncoder.encode(uuid+"_"+fileName, StandardCharsets.UTF_8);
    }

}