package com.kbds.itamserveradmin.domain.assetRequest.entity;

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

    private LocalDateTime astReqMgLogSud;

    @Enumerated(EnumType.STRING)
    private RequestMangeStatus astReqMgLogStatus;

    private String astReqName;


    //==연관관계==//

    @ManyToOne
    @JoinColumn(name = "ast_req_mg_id")
    private AssetRequestManage assetRequestManage;

    //==연관관계==//

}
