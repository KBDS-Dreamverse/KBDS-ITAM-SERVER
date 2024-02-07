package com.kbds.itamserveradmin.domain.department.entity;

import com.kbds.itamserveradmin.domain.cooperation.entity.Cooperation;
import com.kbds.itamserveradmin.domain.purchaseRequest.entity.NewAssetRequest;
import com.kbds.itamserveradmin.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Department {
    @Id
    @Column(name = "dept_id")
    private String deptId;
    @Column(name = "dept_name")
    private String deptName;


    //==연관관계==//

    @ManyToOne
    @JoinColumn(name = "corp_id")
    private Cooperation cooperation;

    @OneToMany(mappedBy = "department",fetch = FetchType.LAZY)
    @Builder.Default
    private List<User> users = new ArrayList<>();

    @OneToMany(mappedBy = "department",fetch = FetchType.LAZY)
    @Builder.Default
    private List<DepartmentAsset> departmentAssets = new ArrayList<>();

    @OneToMany(mappedBy = "department",fetch = FetchType.LAZY)
    @Builder.Default
    private List<NewAssetRequest> newAssetRequests = new ArrayList<>();
    //==연관관계==//
}
