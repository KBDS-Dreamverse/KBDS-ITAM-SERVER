package com.kbds.itamserveradmin.domain.user.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbds.itamserveradmin.domain.asset.entity.AssetNotice;
import com.kbds.itamserveradmin.domain.assetRequest.entity.AssetRequest;
import com.kbds.itamserveradmin.domain.contract.entity.ContractRecord;
import com.kbds.itamserveradmin.domain.corporation.entity.Corporation;
import com.kbds.itamserveradmin.domain.department.entity.Department;
import com.kbds.itamserveradmin.global.entity.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User extends BaseEntity {
    @Id
    private String userId;

    @Column(name = "user_name")
    private String userName;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_role")
    private UserRole userRole;

    @Column(name = "user_photo")
    private String userPhoto;

    @Column(name = "user_contact")
    private String userContact;

    @Column(name = "is_ast_admin")
    private boolean isAstAdmin;


    //==연관관계==//
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dept_id")
    private Department department;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "corp_id")
    private Corporation corporation;




}
