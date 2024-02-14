package com.kbds.itamserveradmin.domain.assetRequest.entity;

import com.kbds.itamserveradmin.global.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AssetRequestManageLog {
    @Id
    private String astReqMgLogId;

    @Enumerated(EnumType.STRING)
    private RequestMangeStatus astReqMgLogStatus;

    private String astReqName;

    private LocalDateTime astReqMgLogSud;

    //==연관관계==//

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ast_req_mg_id")
    private AssetRequestManage assetRequestManage;

    @PrePersist
    public void prePersist() {
        LocalDateTime now = LocalDateTime.now();
        astReqMgLogSud = now;
    }

    @PreUpdate
    public void preUpdate() {
        astReqMgLogSud = LocalDateTime.now();
    }




}