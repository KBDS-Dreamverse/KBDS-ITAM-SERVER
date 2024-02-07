package com.kbds.itamserveradmin.domain.contract.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Contract {
    @Id
    private String contId;


    private Asset ast;
    private Cooperation corp;
    private NewAstReq newAstReq;
    private User user;

    private String contLicTag;
    private int contPrice;
    private String contAdminName;
    private String contVer;
    private OpStatus contOpStatus;
    private LocalDateTime contRegDate;
    private String contName;


}
