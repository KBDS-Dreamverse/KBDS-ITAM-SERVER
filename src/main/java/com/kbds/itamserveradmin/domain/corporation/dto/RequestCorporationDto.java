package com.kbds.itamserveradmin.domain.corporation.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequestCorporationDto {
    int corpId;
    String corpName;
    String crn;
    String corpContact;
    String corpAddr;
    String corpUrl;
    String corpOwner;
    String corpRemarks;
    boolean isSubCorp;

}
