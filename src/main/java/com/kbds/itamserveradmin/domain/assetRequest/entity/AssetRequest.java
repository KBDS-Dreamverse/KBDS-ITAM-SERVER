package com.kbds.itamserveradmin.domain.assetRequest.entity;

import com.kbds.itamserveradmin.domain.assetRequest.entity.RequestStatus;
import com.kbds.itamserveradmin.domain.contract.entity.Contract;
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
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AssetRequest {
    @Id
    @Column(name = "ast_req_id")
    private String astReqId;

    @Column(name = "ast_req_reason")
    private String astReqReason;

    @Column(name = "ast_req_ok_date")
    private LocalDateTime astReqOkDate;

    @Column(name = "ast_req_start_date")
    private LocalDateTime astReqStartDate;

    @Column(name = "ast_req_end_date")
    private LocalDateTime astReqEndDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "ast_req_status")
    private RequestStatus astReqStatus;


    //==연관관계==//

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ast_req_user_id")
    private User astRequestUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cont_id")
    private Contract contract;

    //==연관관계==//

}
