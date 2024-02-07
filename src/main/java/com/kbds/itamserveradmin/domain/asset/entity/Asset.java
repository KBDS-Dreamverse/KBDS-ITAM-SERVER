package com.kbds.itamserveradmin.domain.asset.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

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

    @ElementCollection
    private List<String> astTag = new ArrayList<>();
    private Cooperation corp;
    private User user;

    private String astSwCtgy;
    private String astPrice;
    private String astVer;
    private Boolean isAstInstallFile;
    private String astSpd;
    private String astDpd;




}
