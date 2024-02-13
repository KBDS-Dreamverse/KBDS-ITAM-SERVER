package com.kbds.itamserveradmin.domain.asset.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ManualLogReq {
    private MultipartFile file;

}
