package com.kbds.itamserveradmin.domain.assetRequest.entity;

import com.kbds.itamserveradmin.domain.user.entity.AssetAdmin;
import com.kbds.itamserveradmin.global.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.tomcat.jni.Local;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AssetRequestManage {
    @Id
    private String astReqMgId;

    @Enumerated(EnumType.STRING)
    private RequestMangeStatus astReqMgStatus;

    private LocalDateTime astReqMgDate;
    private LocalDateTime astReqMgSud;

    private String astReqDept;
    private String astReqName;


    //==연관관계==//
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ast_admin_id")
    private AssetAdmin assetAdmin;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ast_req_id")
    private AssetRequest assetRequest;

    @PrePersist
    public void prePersist() {
        LocalDateTime now = LocalDateTime.now();
        astReqMgDate = now;
        astReqMgSud = now;
    }

    @PreUpdate
    public void preUpdate() {
        astReqMgSud = LocalDateTime.now();
    }

}