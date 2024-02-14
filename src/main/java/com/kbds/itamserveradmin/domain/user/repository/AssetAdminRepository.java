package com.kbds.itamserveradmin.domain.user.repository;

import com.kbds.itamserveradmin.domain.user.dto.AssetAdminList;
import com.kbds.itamserveradmin.domain.user.entity.AssetAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AssetAdminRepository extends JpaRepository<AssetAdmin,String> {



    @Query(
            value = "SELECT ast_admin_id, user_name, user_id, dept_name, user_role \n" +
                    "FROM asset_admin\n" +
                    "LEFT JOIN user u on asset_admin.manager_id = u.user_id\n" +
                    "LEFT JOIN department d on u.dept_id = d.dept_id\n" +
                    "where cont_id = :cont_id", nativeQuery = true)
    public List<AssetAdminList> getAssetAdminList(@Param("cont_id")String cont_id);



}
