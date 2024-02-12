package com.kbds.itamserveradmin.domain.corporation.entity;

import com.kbds.itamserveradmin.domain.contract.entity.Contract;
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
public class Corporation {
    @Id
    @Column(name = "corp_id")
    private String corpId;

//    @Id
//    @Column(name = "corp_id")
//    @Enumerated(EnumType.STRING)
//    private CorpPK corpId;

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



}