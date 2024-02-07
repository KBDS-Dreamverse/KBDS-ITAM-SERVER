package com.kbds.itamserveradmin.domain.numOfUsersType.entity;
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
public class NumOfUsersType {

    @Id
    @OneToOne
    @JoinColumn(name = "cont_id")
    private Contract cont;

    private Integer maxUsersLimit;
    private Integer maxCpuLimit;
    private String ipRange;
    private Integer currUsers;
    private Integer currCpu;
    private Integer totalNumPur;
    private Integer totalServer;
    private Integer totalCal;
}
