package com.kbds.itamserveradmin.domain.contract.entity;
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
}
