package com.kbds.itamserveradmin.domain.assetRequest.dto;


import com.kbds.itamserveradmin.domain.assetRequest.entity.AssetRequestManage;
import com.kbds.itamserveradmin.domain.corporation.entity.Corporation;
import com.kbds.itamserveradmin.domain.department.entity.Department;
import com.kbds.itamserveradmin.domain.user.entity.User;
import com.kbds.itamserveradmin.domain.user.entity.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;



@Data
public class AstReqAdminList {

    private String userName;
    private String userRole;
    private String corporation;
    private String department;
    private String status;

    public AstReqAdminList(AssetRequestManage assetRequestManage) {
        User user = assetRequestManage.getAssetAdmin().getManager();
        this.userName = user.getUserName();
        this.userRole = user.getUserRole().getValue();
        this.corporation = user.getCorporation().getCorpName();
        this.department = user.getDepartment().getDeptName();
        this.status = assetRequestManage.getAstReqMgStatus().getValue();
    }
}
