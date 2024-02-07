package com.kbds.itamserveradmin.domain.numOfUsersType.entity;
import com.kbds.itamserveradmin.domain.contract.entity.Contract;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NumOfUsersType implements Serializable {

    @Id
    private String NumOfUsersId;

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
