package com.kbds.itamserveradmin.domain.asset.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ManualLog {
    @Id
    private String mnLogId;

    private String mnLogVer;
    private String mnLogUserGuide;
    private String mnLogInstallFile;
    private String mnLogInstallGuide;


    //==연관관계==//

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ast_id")
    private Asset asset;

    public void updateInstallFilePath(String InsFilePath){
        this.mnLogInstallFile = InsFilePath;
    }
}