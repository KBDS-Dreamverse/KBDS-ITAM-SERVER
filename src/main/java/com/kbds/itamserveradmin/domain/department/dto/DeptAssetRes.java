package com.kbds.itamserveradmin.domain.department.dto;

import com.kbds.itamserveradmin.domain.assetRequest.entity.RequestStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.coyote.Request;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeptAssetRes {
    private String AssetName;
    private String ContNumOfType;
    private String ContPeriodType;
    private String ContSupplyType;
    private String AssetSpd;
    private RequestStatus AssetReqStatus;
    private String AssetImgUrl;
    private String ContId;
}
