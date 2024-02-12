package com.kbds.itamserveradmin.domain.department.entity;

import com.kbds.itamserveradmin.domain.corporation.entity.Corporation;
import com.kbds.itamserveradmin.domain.purchaseRequest.entity.NewAssetRequest;
import com.kbds.itamserveradmin.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Department {


    @Id
    @Column(name = "dept_id")
    private String deptId;
    @Column(name = "dept_name")
    private String deptName;


    //==연관관계==//

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "corp_id")
    private Corporation corporation;

//
//
//    @Id
//    @Column(name = "dept_id")
//    @Enumerated(EnumType.STRING)
//    private DeptPK deptId;
//
//    @Column(name = "dept_name")
//    private String deptName;
//
//
//    //==연관관계==//
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "corp_id")
//    private Corporation corporation;

    //==연관관계==//


}
