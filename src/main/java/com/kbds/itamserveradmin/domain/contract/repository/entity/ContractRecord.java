package com.kbds.itamserveradmin.domain.contract.repository.entity;

import com.kbds.itamserveradmin.domain.user.entity.User;
import com.kbds.itamserveradmin.global.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ContractRecord extends BaseEntity {

    @Id
    private String contractRecordId;

    private String content;

    private RecordType recordType;

    @ManyToOne
    @JoinColumn(nullable = false, name = "user_Id")
    private User user;

    @ManyToOne
    @JoinColumn(nullable = false, name = "contract_id")
    private Contract contract;
}
