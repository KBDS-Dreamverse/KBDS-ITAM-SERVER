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
public class AssetRequestManage extends BaseEntity {
    @Id
    private String astReqMgId;

    @Enumerated(EnumType.STRING)
    private RequestMangeStatus astReqMgStatus;

    private String astReqDept;
    private String astReqName;


    //==연관관계==//
    @ManyToOne
    @JoinColumn(name = "ast_admin_id")
    private AssetAdmin assetAdmin;

    @ManyToOne
    @JoinColumn(name = "ast_req_id")
    private AssetRequest assetRequest;


    @OneToMany(mappedBy = "assetRequestManage",fetch = FetchType.LAZY)
    @Builder.Default
    private List<AssetRequestManageLog> assetRequestManageLogs = new ArrayList<>();
}
