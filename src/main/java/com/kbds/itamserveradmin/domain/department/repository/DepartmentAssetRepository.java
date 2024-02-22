package com.kbds.itamserveradmin.domain.department.repository;

import com.kbds.itamserveradmin.domain.contract.entity.Contract;
import com.kbds.itamserveradmin.domain.department.entity.Department;
import com.kbds.itamserveradmin.domain.department.entity.DepartmentAsset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DepartmentAssetRepository extends JpaRepository<DepartmentAsset,String> {
//    List<DepartmentAsset> findBy (Iterable<String> strings);
    @Query("select da.contract from DepartmentAsset da where da.department =:department")
    List<Contract> findContractByDept(@Param("department") Department department);
}
