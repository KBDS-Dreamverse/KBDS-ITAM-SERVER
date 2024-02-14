package com.kbds.itamserveradmin.domain.corporation.dto;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterCorporationDto {
    String corpName;
    String crn;
    String corpContact;
    String corpAddr;
    String corpUrl;
    String corpOwner;
    String corpRemarks;
    boolean isSubCorp;
}
