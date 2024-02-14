package com.kbds.itamserveradmin.domain.contract.entity;

import com.kbds.itamserveradmin.domain.contract.dto.request.NumberOfUsersReq;
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
public class NumOfUsersType {

    @Id
    private String NumOfUsersId;

    @OneToOne
    @JoinColumn(name = "cont_id")
    private Contract cont;

    private Integer maxUsersLimit;
    private Integer maxCoreLimit;
    private String ipRange;
    private Integer currUsers;
    private Integer currCore;
    private Integer totalNumPur;
    private Integer totalServer;
    private Integer totalCal;

    public static NumOfUsersType toEntity(Contract contract, NumberOfUsersReq numberOfUsersReq) {
        return NumOfUsersType.builder()
                .cont(contract)
                .maxUsersLimit(numberOfUsersReq.getMaxUsersLimit())
                .maxCoreLimit(numberOfUsersReq.getMaxCoreLimit())
                .ipRange(numberOfUsersReq.getIpRange())
                .currUsers(numberOfUsersReq.getCurrentUsers())
                .currCore(numberOfUsersReq.getCurrentCore())
                .totalNumPur(numberOfUsersReq.getTotalNumPur())
                .totalServer(numberOfUsersReq.getTotalServer())
                .totalCal(numberOfUsersReq.getTotalCal())
                .build();
    }
}
