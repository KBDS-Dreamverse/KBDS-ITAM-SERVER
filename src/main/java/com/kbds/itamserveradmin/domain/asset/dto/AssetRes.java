package com.kbds.itamserveradmin.domain.asset.dto;
import com.kbds.itamserveradmin.domain.asset.entity.Asset;
import com.kbds.itamserveradmin.domain.asset.entity.AstTag;
import com.kbds.itamserveradmin.domain.corporation.entity.Corporation;
import com.kbds.itamserveradmin.domain.user.entity.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AssetRes {

    private String astId;
    private String astName;
    private Boolean isAstInternal;
    private AstTag astTag;
    private Corporation corp;
    private User user;
    private String astSwCtgy;
    private String astPrice;
    private String astVer;
    private Boolean isAstInstallFile;
    private String astSpd;
    private String astDpd;

    public static AssetRes assetInfo (Asset asset){
        return AssetRes.builder()
                .astId(asset.getAstId())
                .astName(asset.getAstName())
                .astTag(asset.getAstTag())
                .astDpd(asset.getAstDpd())
                .astSpd(asset.getAstSpd())
                .astVer(asset.getAstVer())
                .astPrice(asset.getAstPrice())
                .build();
    }

}
