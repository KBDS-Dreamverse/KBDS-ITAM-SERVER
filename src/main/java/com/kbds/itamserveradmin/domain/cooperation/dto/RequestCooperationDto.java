package com.kbds.itamserveradmin.domain.cooperation.dto;

import com.kbds.itamserveradmin.domain.cooperation.entity.Cooperation;
import lombok.*;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequestCooperationDto {
    String corpName;
    String crn;
    String corpContact;
    String corpAddr;
    String corpUrl;
    String corpOwner;
    String corpRemarks;
    boolean isSubCorp;

}
