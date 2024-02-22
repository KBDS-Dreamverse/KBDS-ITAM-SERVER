package com.kbds.itamserveradmin.domain.department.repository;

import com.kbds.itamserveradmin.domain.corporation.entity.Corporation;
import com.kbds.itamserveradmin.domain.department.dto.DeptInfoRes;
import com.kbds.itamserveradmin.domain.department.entity.Department;
import com.kbds.itamserveradmin.domain.department.entity.DepartmentAsset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department,String> {
    @Query("select NEW com.kbds.itamserveradmin.domain.department.dto.DeptInfoRes(d.deptId, d.deptName) from Department d where d.corporation =:corporation")
    List<DeptInfoRes> findNameByCorp(@Param("corporation") Corporation corporation);

    Department findDepartmentByDeptId(String deptId);
}
