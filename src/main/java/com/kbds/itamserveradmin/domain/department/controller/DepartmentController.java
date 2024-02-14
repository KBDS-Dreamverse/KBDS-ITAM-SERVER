package com.kbds.itamserveradmin.domain.department.controller;

import com.kbds.itamserveradmin.domain.department.dto.DeptAssetRes;
import com.kbds.itamserveradmin.domain.department.dto.DeptInfoRes;
import com.kbds.itamserveradmin.domain.department.service.DepartmentService;
import com.kbds.itamserveradmin.domain.user.dto.UserInfoRes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/kbitam")
@CrossOrigin(origins ="*", allowedHeaders = "*")
public class DepartmentController {
    private final DepartmentService departmentService;

    @GetMapping("/dept")
    public ResponseEntity<List<DeptInfoRes>> getDeptInfo(@RequestParam("corpName") String corpName) {
        return ResponseEntity.ok(departmentService.DeptInfo(corpName));
    }
    @GetMapping("/dept/asset")
    public ResponseEntity<List<DeptAssetRes>> getDeptAsset(@Param("deptId") String deptId, @Param("userId") String userId){
        return ResponseEntity.ok(departmentService.getDeptAsset(deptId,userId));
    }


}
