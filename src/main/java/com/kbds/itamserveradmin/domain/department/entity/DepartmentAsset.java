package com.kbds.itamserveradmin.domain.department.entity;

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
public class DepartmentAsset {
    @Id
    @Column(name = "dept_ast_id")
    private String DeptAstId;

    //==연관관계==//
    @ManyToOne
    @JoinColumn(name = "dept_id")
    private Department department;

//    @ManyToOne
//    @JoinColumn(name = "contract_id")
//    private Contract contract;

    //==연관관계==//

}
