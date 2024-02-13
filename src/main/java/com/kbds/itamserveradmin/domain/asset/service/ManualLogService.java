package com.kbds.itamserveradmin.domain.asset.service;

import com.kbds.itamserveradmin.domain.asset.dto.ManualLogReq;
import com.kbds.itamserveradmin.domain.asset.entity.Asset;
import com.kbds.itamserveradmin.domain.asset.entity.ManualLog;
import com.kbds.itamserveradmin.domain.asset.repository.ManualLogRepository;
import com.kbds.itamserveradmin.global.file.FileUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ManualLogService {
    private final ManualLogRepository manualLogRepository;
    private final FileUploadService fileUploadService;

    public ManualLog findByAsset_AstId(String astId) {
        return manualLogRepository.findByAsset_AstId(astId);
    }

    public List<ManualLog> findByAstId(Asset asset) {
        return manualLogRepository.findByAsset(asset);
    }
    //일단 로컬에 파일 업로드 로직을 작성했습니다.(asset에 연결 아직못함)
    @Transactional
    public void saveInstallFile(ManualLogReq manualLogReq)throws IOException{
        ManualLog manualLog = new ManualLog();
        String filePath = fileUploadService.saveFile(manualLogReq.getFile());
        manualLog.updateInstallFilePath(filePath);

        manualLogRepository.save(manualLog);
    }

    public ManualLog findByMnLogId(String mnLogId){
        return manualLogRepository.findByMnLogId(mnLogId);
    }
}
