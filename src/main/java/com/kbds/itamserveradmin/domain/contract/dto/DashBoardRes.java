package com.kbds.itamserveradmin.domain.contract.dto;

import lombok.Data;

@Data
public class DashBoardRes {

    private String contName;

    /*라이선스 이름들*/
    private String licPeriod;
    private String licUsers;
    private String licSupply;

}
