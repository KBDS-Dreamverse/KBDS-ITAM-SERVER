package com.kbds.itamserveradmin.domain.asset.entity;

import com.kbds.itamserveradmin.domain.corporation.entity.Corporation;
import com.kbds.itamserveradmin.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Asset {

    @Id
    private String astId;
    private String astName;
    private Boolean isAstInternal;

    @Enumerated(EnumType.STRING)
    private AstTag astTag;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "corp_id" )
    private Corporation corp;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String astSwCtgy;
    private String astPrice;
    private String astVer;
    private Boolean isAstInstallFile;
    private String astSpd;
    private String astDpd;





}
