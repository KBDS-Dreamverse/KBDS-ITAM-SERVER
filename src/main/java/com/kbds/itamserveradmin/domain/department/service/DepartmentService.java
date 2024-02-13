package com.kbds.itamserveradmin.domain.department.service;

import com.kbds.itamserveradmin.domain.department.dto.DeptAssetRes;
import com.kbds.itamserveradmin.domain.department.dto.DeptInfoRes;
import org.springframework.stereotype.Service;

import java.util.List;


public interface DepartmentService {
    public List<DeptInfoRes> DeptInfo(String corpName);
    public List<DeptAssetRes> getDeptAsset(String deptId,String userId);

}
