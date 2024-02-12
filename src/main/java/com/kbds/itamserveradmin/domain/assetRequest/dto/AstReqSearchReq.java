package com.kbds.itamserveradmin.domain.assetRequest.dto;


import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Data
public class AstReqSearchReq  implements Serializable {


    private String userId;
    private LocalDateTime start;
    private LocalDateTime end;


}
