package com.kbds.itamserveradmin.domain.purchaseRequest.entity;

import com.kbds.itamserveradmin.domain.department.entity.Department;
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
public class NewAssetRequestLog {
    @Id
    private String newAstReqLogId;

    private String newAstReqName;

    @Enumerated(EnumType.STRING)
    private PurchaseRequestStatus newAstReqStatus;

    private LocalDateTime newAstReqDate;

    private LocalDateTime newAstReqSud;

    private String newAstReqUsePlan;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dept_id")
    private Department department;

}
