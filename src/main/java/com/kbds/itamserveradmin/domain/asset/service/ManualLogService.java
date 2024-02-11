package com.kbds.itamserveradmin.domain.asset.service;

import com.kbds.itamserveradmin.domain.asset.entity.ManualLog;
import com.kbds.itamserveradmin.domain.asset.repository.ManualLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ManualLogService {
    private final ManualLogRepository manualLogRepository;

    public ManualLog findByAsset_AstId(String astId){
        return manualLogRepository.findByAsset_AstId(astId);
    }

//    //파일을 가져온다.. 넣지말고 일단 디비에 파일이 이
//    public void getFile(ManualLog manualLog, MultipartFile multipartFile){
//        String filePath = System.getProperty("");
//        UUID uuid = UUID.randomUUID();
//        String fileName = uuid + "_" + multipartFile.getOriginalFilename();
//        File saveFile = new File();

//    }
}
