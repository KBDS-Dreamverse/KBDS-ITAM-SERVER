package com.kbds.itamserveradmin.domain.contract.entity;

import com.kbds.itamserveradmin.domain.asset.entity.Asset;
import com.kbds.itamserveradmin.domain.cooperation.entity.Cooperation;
import com.kbds.itamserveradmin.domain.purchaseRequest.entity.NewAssetRequest;
import com.kbds.itamserveradmin.domain.user.entity.User;
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
public class Contract {
    @Id
    private String contId;


    @OneToOne
    @JoinColumn(name = "ast_id")
    private Asset ast;

    @ManyToOne
    @JoinColumn(name = "corp_id")
    private Cooperation corp;

    @OneToOne
    private NewAssetRequest newAstReq;

    private User user;


    private String contLicTag;
    private int contPrice;
    private String contAdminName;
    private String contVer;
    private OpStatus contOpStatus;
    private LocalDateTime contRegDate;
    private String contName;


}
