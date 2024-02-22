package com.kbds.itamserveradmin.domain.user.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.SqlResultSetMapping;


//@NoArgsConstructor
//@AllArgsConstructor

public interface AssetAdminList {

//    private String ast_admin_id; //관리자id
//    private String user_name; //유저이름
//    private String user_id; //유저 사번
//    private String dept_name; //부서 이름
//    private String user_role; //유저 직급

    String getAst_admin_id();
    String getUser_name();
    String getUser_id();
    String getDept_name();
    String getUser_role();
//    public String getAst_admin_id() {
//        return ast_admin_id;
//    }
//
//    public String getUser_name() {
//        return user_name;
//    }
//
//    public String getUser_id() {
//        return user_id;
//    }
//
//    public String getDept_name() {
//        return dept_name;
//    }
//
//    public String getUser_role() {
//        return user_role;
//    }
}
