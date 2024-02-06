package com.kbds.itamserveradmin.domain.user.entity;


import com.kbds.itamserveradmin.domain.asset.entity.AssetNotice;
import com.kbds.itamserveradmin.domain.assetRequest.entity.AssetRequest;
import com.kbds.itamserveradmin.domain.contract.entity.ContractLog;
import com.kbds.itamserveradmin.domain.cooperation.entity.Cooperation;
import com.kbds.itamserveradmin.domain.department.entity.Department;
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
public class User {
    @Id
    @Column(name = "user_id")
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
    @ManyToOne
    @JoinColumn(name = "dept_id")
    private Department department;

    @ManyToOne
    @JoinColumn(name = "corp_id")
    private Cooperation cooperation;

    @OneToMany(mappedBy = "user")
    private List<UserLog> userLogs = new ArrayList<>();

    @OneToMany(mappedBy = "writer")
    private List<AssetNotice> assetNotices = new ArrayList<>();
    //==연관관계==//

    @OneToMany(mappedBy = "manager")
    private List<AssetAdmin> assetAdmins = new ArrayList<>();

    @OneToMany(mappedBy = "astRequestUser")
    private List<AssetRequest> assetRequests = new ArrayList<>();

    @OneToMany(mappedBy = "editor")
    private List<ContractLog> contractLogs = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<NewAssetRequestAdmin> newAssetRequestAdmins = new ArrayList<>();
}
