package com.kbds.itamserveradmin.domain.user.entity;

import com.kbds.itamserveradmin.domain.contract.entity.Contract;
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
public class AssetAdmin {
    @Id
    private String astAdminId;

    //==연관관계==//

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_id")
    private User manager;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cont_id")
    private Contract contract;
}
