package com.kbds.itamserveradmin.domain.department.repository;

import com.kbds.itamserveradmin.domain.department.entity.DepartmentAsset;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepartmentAssetRepository extends JpaRepository<DepartmentAsset,String> {
//    List<DepartmentAsset> findBy (Iterable<String> strings);
}
