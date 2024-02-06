package com.kbds.itamserveradmin.domain.user.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AssetAdmin {
    @Id
    private String astAdminId;

    //==연관관계==//

    @ManyToOne
    @JoinColumn(name = "manager_id")
    private User manager;

//    @ManyToOne
//    @JoinColumn(name = "cont_id")
//    private Contract contract;
}
