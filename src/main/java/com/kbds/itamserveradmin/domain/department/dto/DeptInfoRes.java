package com.kbds.itamserveradmin.domain.department.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeptInfoRes {
    private String DeptId;
    private String DeptName;
}
