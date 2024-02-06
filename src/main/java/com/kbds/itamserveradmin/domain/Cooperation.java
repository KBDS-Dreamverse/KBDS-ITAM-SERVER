package com.kbds.itamserveradmin.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cooperation {
    @Id
    private String corpId;

    private String corpName;
    private String crn;  // 사업자 번호가 list인가요 ?!
    private String corpContact;
    private String corpAddr;
    private String corpSite;
    private String corpHead;
    private String corpNote;
    private boolean isSubCorp;
}
