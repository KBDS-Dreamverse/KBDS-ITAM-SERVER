package com.kbds.itamserveradmin.domain.assetRequest.dto;


import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
//@JsonPropertyOrder({"astReqId", "astReqMgIdList"})
public class AssetRequestRes {


    private String astReqId;

    private List<String> astReqMgIdList;

    public static AssetRequestRes of(String astReqId, List<String> astReqMgIdList){
        return AssetRequestRes.builder().astReqId(astReqId).astReqMgIdList(astReqMgIdList).build();

    }
}


