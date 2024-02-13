package com.kbds.itamserveradmin.domain.asset.entity;

import com.kbds.itamserveradmin.domain.cooperation.entity.Cooperation;
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
    private Long astId;
    private String astName;
    private Boolean isAstInternal;
    private AstTag astTag;

    @ManyToOne
    @JoinColumn(name = "corp_id" )
    private Cooperation corp;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String astSwCtgy;
    private String astPrice;
    private String astVer;
    private Boolean isAstInstallFile;
    private String astSpd;
    private String astDpd;

}
