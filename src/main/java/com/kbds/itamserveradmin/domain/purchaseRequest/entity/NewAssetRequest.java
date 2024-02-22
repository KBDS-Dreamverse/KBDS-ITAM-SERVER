package com.kbds.itamserveradmin.domain.purchaseRequest.entity;

import com.kbds.itamserveradmin.domain.department.entity.Department;
import com.kbds.itamserveradmin.domain.user.entity.NewAssetRequestAdmin;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewAssetRequest {
    @Id
    private String newAstReqId;

    private String newAstReqName;

    @Enumerated(EnumType.STRING)
    private PurchaseRequestStatus newAstReqStatus;

    private LocalDateTime newAstReqDate;

    private LocalDateTime newAstReqSud;

    private String newAstReqUsePlan;


    //==연관관계==//

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dept_id")
    private Department department;

}
