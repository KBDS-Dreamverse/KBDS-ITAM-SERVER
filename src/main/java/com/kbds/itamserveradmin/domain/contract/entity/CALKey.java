package com.kbds.itamserveradmin.domain.contract.entity;

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
public class CALKey {
    @Id
    private String calId;

    private KeyStatus calKeyStatus;

    //==연관관계==//
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cont_id")
    private Contract contract;
}
