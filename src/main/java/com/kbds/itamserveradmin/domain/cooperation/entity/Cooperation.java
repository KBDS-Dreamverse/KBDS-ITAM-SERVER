package com.kbds.itamserveradmin.domain.cooperation.entity;

import com.kbds.itamserveradmin.domain.contract.entity.Contract;
import com.kbds.itamserveradmin.domain.department.entity.Department;
import com.kbds.itamserveradmin.domain.user.entity.User;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "cooperation")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cooperation {

    @Id
    @Column(name = "corp_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int corpId;

    @Column(name = "corp_name")
    private String corpName;

    @Column(name = "crn")
    private String crn;

    @Column(name = "corp_contact")
    private String corpContact;

    @Column(name = "corp_addr")
    private String corpAddr;

    @Column(name = "corp_url")
    private String corpUrl;

    @Column(name = "corp_owner")
    private String corpOwner;

    @Column(name = "corp_remarks")
    private String corpRemarks;

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

    public Cooperation(String corpName, String crn, String corpContact, String corpAddr, String corpUrl, String corpOwner, String corpRemarks, boolean isSubCorp) {
        this.corpName = corpName;
        this.crn = crn;
        this.corpContact = corpContact;
        this.corpAddr = corpAddr;
        this.corpUrl = corpUrl;
        this.corpRemarks = corpRemarks;
        this.corpOwner = corpOwner;
        this.isSubCorp = isSubCorp;
    }


    //==연관관계==//


}
