package com.kbds.itamserveradmin.domain.user.entity;

import com.kbds.itamserveradmin.domain.purchaseRequest.entity.NewAssetRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewAssetRequestAdmin {
    @EmbeddedId
    private NewAssetReqeustAdminId newAstReqAdminId;

    @MapsId("userId")
    @ManyToOne
    @JoinColumn(name = "ast_admin_id")
    private User user;

    @MapsId("newAstReqId")
    @ManyToOne
    @JoinColumn(name = "new_ast_req_id")
    private NewAssetRequest newAssetRequest;
}
