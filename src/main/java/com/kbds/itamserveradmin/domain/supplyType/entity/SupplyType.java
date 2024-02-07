package com.kbds.itamserveradmin.domain.supplyType.entity;
import com.kbds.itamserveradmin.domain.contract.repository.entity.Contract;
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
}
