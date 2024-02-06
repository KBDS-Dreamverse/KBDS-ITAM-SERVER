package com.kbds.itamserveradmin.domain.purchaseRequest.entity;

import com.kbds.itamserveradmin.domain.cooperation.entity.Cooperation;
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
public class AssetPurchaseRequest {
    @Id
    @Column(name = "ast_pur_req_id)")
    private String astPurReqId;

    @Column(name = "ast_pur_req_name")
    private String astPurReqName;

    @Enumerated(EnumType.STRING)
    private PurchaseRequestStatus astPurReqStatus;

    private Long astPurReqLicCnt;
    private LocalDateTime astPurReqContStartDate;
    private LocalDateTime astPurReqContEndDate;
    private LocalDateTime astPurReqDate;
    private String astPurReqVer;

    //==연관관계==//
    @ManyToOne
    @JoinColumn(name = "corp_id")
    private Cooperation cooperation;

//    @ManyToOne
//    @JoinColumn(name = "ast_id")
//    private Asset asset;
    //==연관관계==//
}
