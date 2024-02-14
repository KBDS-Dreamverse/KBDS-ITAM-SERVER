package com.kbds.itamserveradmin.domain.assetRequest.entity;

import com.kbds.itamserveradmin.domain.contract.dto.CalKeyRes;
import com.kbds.itamserveradmin.domain.contract.entity.CALKey;
import com.kbds.itamserveradmin.domain.contract.entity.LicenseKey;
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
public class UserAssetRequestInfo {
    @Id
    private String userAstReqInfoId;

    private String userIp;

    private String userPort;

    @Convert(converter = LicneseCategoryConverter.class)
    private LicenseCategory licCtgy;

    @OneToOne
    private CALKey calKey;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lic_key_id")
    private LicenseKey licenseKey;

    @OneToOne
    private AssetRequest assetRequest;


}