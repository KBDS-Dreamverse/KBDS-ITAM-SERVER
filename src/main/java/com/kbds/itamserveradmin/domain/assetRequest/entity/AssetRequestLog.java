package com.kbds.itamserveradmin.domain.assetRequest.entity;

import com.kbds.itamserveradmin.domain.user.entity.User;
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
public class AssetRequestLog extends BaseEntity {
    @Id
    @Column(name = "ast_req_log_id")
    private String astReqLogId;

    @Enumerated(EnumType.STRING)
    private RequestStatus astReqLogStatus;

    private String astReqVer;

    private String astReqName;

    private Long astReqCnt;


    //==연관관계==//
    @ManyToOne
    @JoinColumn(name = "ast_req_id")
    private AssetRequest assetRequest;

    @ManyToOne
    @JoinColumn(name = "ast_req_user_id")
    private User assetRequestUser;
    //==연관관계==//

}
