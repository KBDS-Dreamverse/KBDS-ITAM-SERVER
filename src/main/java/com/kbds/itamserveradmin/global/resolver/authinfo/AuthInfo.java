package com.kbds.itamserveradmin.global.resolver.authinfo;


import com.kbds.itamserveradmin.global.constant.RoleType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class AuthInfo {
    private String token;
    private String email;
    private List<RoleType> role;
}
