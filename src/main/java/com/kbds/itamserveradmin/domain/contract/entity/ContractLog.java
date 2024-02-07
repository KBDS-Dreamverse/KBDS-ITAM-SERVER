package com.kbds.itamserveradmin.domain.contract.entity;

import com.kbds.itamserveradmin.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContractLog {
    @Id
    private String ContLogId;

    private String ContLogContent;
    private String ContLogType;
    private LocalDateTime ContSud;


    //==연관관계==//

    @ManyToOne
    @JoinColumn(name = "editor_id")
    private User editor;

//    @ManyToOne
//    @JoinColumn(name = "contract_id")
//    private Contract contract;
}
