package com.kbds.itamserveradmin.domain.contract.dto.request;

import lombok.Getter;

@Getter
public class NumberOfUsersReq {
    private int currentCPU;
    private int currentUsers;
    private String ipRange;
    private int maxCPULimit;
    private int maxUsersLimit;
    private int totalCal;
    private int totalNumPur;
    private int totalServer;
    private String numOfUsersId;
    private int currentCore;
    private int maxCoreLimit;
}
