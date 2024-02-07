package com.kbds.itamserveradmin.domain.asset.entity;

import com.kbds.itamserveradmin.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.tomcat.util.http.parser.ContentRange;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AssetNotice {
    @Id
    @Column(name = "ast_ntc_id")
    private String astNtcId;

    @Column(name = "ast_ntc_content")
    private String astNtcContent;


    //==연관관계==//
//    @ManyToOne
//    @JoinColumn(name = "contract_id")
//    private Contract contract;

    @ManyToOne
    @JoinColumn(name = "writer_id")
    private User writer;

}
