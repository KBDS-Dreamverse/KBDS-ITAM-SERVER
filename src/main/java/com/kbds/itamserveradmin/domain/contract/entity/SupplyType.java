package com.kbds.itamserveradmin.domain.contract.entity;
import com.kbds.itamserveradmin.domain.contract.dto.request.SupplyLicense;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SupplyType {

    @Id
    private String splyId;

    @OneToOne
    @JoinColumn(name = "cont_id")
    private Contract cont;

    private String splyInstallFile;
    private String splyInstallGuide;
    private String acsUrl;
    private String splyVer;
    private String splyUserGuide;

    public static SupplyType toEntity(Contract contract, SupplyLicense licenseSupplyReq) {
        return SupplyType.builder()
                .cont(contract)
                .splyInstallFile(licenseSupplyReq.getSupplyInstallFile())
                .splyInstallGuide(licenseSupplyReq.getSupplyInstallGuide())
                .acsUrl(licenseSupplyReq.getAcsUrl())
                .splyVer(licenseSupplyReq.getSupplyVersion())
                .splyUserGuide(licenseSupplyReq.getSupplyUserGuide())
                .build();
    }
}
