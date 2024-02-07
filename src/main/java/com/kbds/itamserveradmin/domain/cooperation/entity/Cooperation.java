package com.kbds.itamserveradmin.domain.cooperation.entity;

import com.kbds.itamserveradmin.domain.contract.repository.entity.Contract;
import com.kbds.itamserveradmin.domain.department.entity.Department;
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
public class Cooperation {
    @Id
    @Column(name = "corp_id")
    private String corpId;

    @Column(name = "corp_name")
    private String corpName;

    @Column(name = "crn")
    private String corpNum;

    @Column(name = "corp_contact")
    private String corpContact;

    @Column(name = "corp_addr")
    private String corpAddr;

    @Column(name = "corp_site")
    private String corpSite;

    @Column(name = "corp_head")
    private String corpHead;

    @Column(name = "corp_note")
    private String corpNote;

    @Column(name = "is_sub_corp")
    private boolean isSubCorp;

    //==연관관계==//
    @OneToMany(mappedBy = "cooperation",fetch = FetchType.LAZY)
    @Builder.Default
    private List<Department> departments = new ArrayList<>();

    @OneToMany(mappedBy = "cooperation",fetch = FetchType.LAZY)
    @Builder.Default
    private List<User> users = new ArrayList<>();

//    @OneToMany(mappedBy ="cooperation")
//    private List<Asset> assets = new ArrayList<>();

    @OneToMany(mappedBy = "corp",fetch = FetchType.LAZY)
    @Builder.Default
    private List<Contract> contracts = new ArrayList<>();

    //==연관관계==//


}
