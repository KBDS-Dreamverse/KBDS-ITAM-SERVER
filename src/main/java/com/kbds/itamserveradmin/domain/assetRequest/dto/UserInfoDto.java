package com.kbds.itamserveradmin.domain.assetRequest.dto;


import com.kbds.itamserveradmin.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserInfoDto {

    private String userName;
    private String userRole;
    private String department;
    private String corporation;

    public static UserInfoDto from(User u){
        return UserInfoDto.builder()
                .userName(u.getUserName())
                .userRole(u.getUserRole().getValue())
                .department(u.getDepartment().getDeptName())
                .corporation(u.getCorporation().getCorpName())
                .build();
    }
}
