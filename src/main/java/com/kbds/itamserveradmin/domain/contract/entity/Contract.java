package com.kbds.itamserveradmin.domain.contract.entity;

import com.kbds.itamserveradmin.domain.asset.entity.Asset;
import com.kbds.itamserveradmin.domain.corporation.entity.Corporation;
import com.kbds.itamserveradmin.domain.purchaseRequest.entity.NewAssetRequest;
import com.kbds.itamserveradmin.domain.user.entity.User;
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
public class Contract {

    @Id
    private String contId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ast_id")
    private Asset ast;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "corp_id")
    private Corporation corp;

    @OneToOne
    private NewAssetRequest newAstReq;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usr_id")
    private User user;


    private LocalDateTime contRegDate;
    private String contName;
    private String contLicTag;
    private Integer contPrice;
    private String contAdminName;
    private String contVer;
    private OpStatus contOpStatus;
    private LocalDateTime contRegDate;

    @PrePersist
    public void prePersist() {
        LocalDateTime now = LocalDateTime.now();
        contRegDate = now;
    }

}
