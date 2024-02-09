package com.kbds.itamserveradmin.domain.assetRequest.dto;


import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AssetRequestRes {


    private String astReqId;

    public static AssetRequestRes of(String astReqId){
        return AssetRequestRes.builder().astReqId(astReqId).build();

    }
}


