package com.kbds.itamserveradmin.domain.asset.dto;

import com.kbds.itamserveradmin.domain.asset.entity.ManualLog;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ManualLogRes {

    private String mnLogId;
    private String mnLogVer;
    private String mnLogUserGuide;
    private String mnLogInstallFile;
    private String mnLogInstallGuide;
    private String assetId;

    public static ManualLogRes installGuideRes(ManualLog manualLog){
        return ManualLogRes.builder()
                .assetId(manualLog.getAsset().getAstId())
                .mnLogId(manualLog.getMnLogId())
                .mnLogVer(manualLog.getMnLogVer())
                .mnLogInstallFile(manualLog.getMnLogInstallFile())
                .mnLogInstallGuide(manualLog.getMnLogInstallGuide())
                .build();
    }

    public static ManualLogRes allvers(ManualLog manualLog){
        return ManualLogRes.builder()
                .assetId(manualLog.getAsset().getAstId())
                .mnLogVer(manualLog.getMnLogVer())
                .build();
    }
}
