package com.kbds.itamserveradmin.domain.user.entity;

import com.kbds.itamserveradmin.domain.purchaseRequest.entity.NewAssetRequest;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Data
@Embeddable
public class NewAssetReqeustAdminId implements Serializable {

    private String userId;
    private String newAstReqId;
}
