package com.kbds.itamserveradmin.domain.Contract;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Contract {

    @Id
    private String contId;

    @ManyToOne
    @JoinColumn(name = "ast_id")
    private String astId;

    private String corpId;
    private String newAstReqId;
    private String userId;

    private String contLicTag;
    private int contPrice;
    private String contAdminName;
    private String contVer;
    private OpStatus contOpStatus;
    private LocalDateTime contRegDate;
    private String contName;



}
