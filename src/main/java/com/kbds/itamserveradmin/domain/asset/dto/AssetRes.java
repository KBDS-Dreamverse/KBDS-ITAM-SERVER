package com.kbds.itamserveradmin.domain.asset.dto;
import com.kbds.itamserveradmin.domain.asset.entity.Asset;
import com.kbds.itamserveradmin.domain.asset.entity.AstTag;
import com.kbds.itamserveradmin.domain.assetRequest.entity.AssetRequest;
import com.kbds.itamserveradmin.domain.assetRequest.entity.RequestStatus;
import com.kbds.itamserveradmin.domain.user.entity.User;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class AssetRes {

    private String astId;
    private String astName;
    private Boolean isAstInternal;
    private AstTag astTag;
    private String corpName;
    private User user;
    private String astSwCtgy;
    private String astPrice;
    private String astVer;
    private Boolean isAstInstallFile;
    private String astSpd;
    private String astDpd;
    private RequestStatus requestStatus;
    private List<String> mnLogVersList;

    public static AssetRes assetInfo (Asset asset, AssetRequest ar, List<String> mnLogVersList){
        return AssetRes.builder()
                .astId(asset.getAstId())
                .astName(asset.getAstName())
                .astTag(asset.getAstTag())
                .astDpd(asset.getAstDpd())
                .astSpd(asset.getAstSpd())
                .astVer(asset.getAstVer())
                .astPrice(asset.getAstPrice())
                .corpName(asset.getCorp().getCorpName())
                .requestStatus(ar.getAstReqStatus())
                .mnLogVersList(mnLogVersList)
                .build();
    }
}
