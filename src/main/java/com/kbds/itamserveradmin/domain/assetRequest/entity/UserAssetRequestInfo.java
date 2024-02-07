package com.kbds.itamserveradmin.domain.assetRequest.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserAssetRequestInfo {
    @Id
    private String userAstReqInfoId;

    private String userIp;

    private String userPort;

    @Convert(converter = LicneseCategoryConverter.class)
    private LicenseCategory licCtgy;

}
