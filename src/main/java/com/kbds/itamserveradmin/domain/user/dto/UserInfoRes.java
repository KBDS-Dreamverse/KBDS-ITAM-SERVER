package com.kbds.itamserveradmin.domain.user.dto;


import com.kbds.itamserveradmin.domain.user.entity.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserInfoRes {
    private String userId;
    private String userName;
    private String userPhoto;
    private UserRole userRole;
    private String DeptName;
    private String CorpName;

}
