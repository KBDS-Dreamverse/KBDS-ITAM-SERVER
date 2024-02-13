package com.kbds.itamserveradmin.domain.department.service;

import com.kbds.itamserveradmin.domain.asset.entity.Asset;
import com.kbds.itamserveradmin.domain.asset.repository.AssetRepository;
import com.kbds.itamserveradmin.domain.assetRequest.entity.AssetRequest;
import com.kbds.itamserveradmin.domain.assetRequest.entity.RequestStatus;
import com.kbds.itamserveradmin.domain.assetRequest.repository.AssetRequestRepository;
import com.kbds.itamserveradmin.domain.contract.entity.Contract;
import com.kbds.itamserveradmin.domain.contract.repository.ContractRepository;
import com.kbds.itamserveradmin.domain.corporation.entity.Corporation;
import com.kbds.itamserveradmin.domain.corporation.repository.CorporationRepository;
import com.kbds.itamserveradmin.domain.department.dto.DeptAssetRes;
import com.kbds.itamserveradmin.domain.department.dto.DeptInfoRes;
import com.kbds.itamserveradmin.domain.department.entity.Department;
import com.kbds.itamserveradmin.domain.department.repository.DepartmentAssetRepository;
import com.kbds.itamserveradmin.domain.department.repository.DepartmentRepository;
import com.kbds.itamserveradmin.domain.user.entity.User;
import com.kbds.itamserveradmin.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService{

    private final DepartmentRepository departmentRepository;
    private final CorporationRepository corporationRepository;
    private final UserRepository userRepository;
    private final DepartmentAssetRepository departmentAssetRepository;
    private final AssetRepository assetRepository;
    private final AssetRequestRepository assetRequestRepository;

    @Override
    public List<DeptInfoRes> DeptInfo(String corpName) {
        Corporation corporation = corporationRepository.findCorporationByCorpName(corpName);
        List<DeptInfoRes> departList = departmentRepository.findNameByCorp(corporation);
        return departList;
    }

    @Override
    public List<DeptAssetRes> getDeptAsset(String deptId, String userId) {
        Department department = departmentRepository.findDepartmentByDeptId(deptId);
        List<Contract> ContractList = departmentAssetRepository.findContractByDept(department);
        User user = userRepository.findUserByUserId(userId);

        Map<Character, String> supplyTypes = Map.of(
                '1', "패키지",
                '2', "Low Volume",
                '3', "High Volume",
                '4', "SaaS"
        );

        Map<Character, String> periodTypes = Map.of(
                '1', "구독",
                '2', "영구"
        );

        Map<Character, String> userTypes = Map.of(
                '1', "동시 사용자수",
                '2', "사이트",
                '3', "코어",
                '4', "1pc1copy",
                '5', "서버접속"
        );


        List<DeptAssetRes> deptAssetResList = new ArrayList<>();
        for(Contract cont : ContractList){
            Asset asset = assetRepository.findAssetByAstId(cont.getAst().getAstId());
            AssetRequest assetRequest = assetRequestRepository.findAssetRequestByContractAndAstRequestUser(cont,user);

            String contLicTag= cont.getContLicTag();
            char supplyChar = contLicTag.charAt(0);
            char periodChar = contLicTag.charAt(1);
            char userChar = contLicTag.charAt(2);

            if (assetRequest==null){
                DeptAssetRes deptAssetRes = DeptAssetRes.builder().
                        AssetName(asset.getAstName()).AssetReqStatus(RequestStatus.NOT_IN_USE).AssetSpd(asset.getAstSpd()).AssetImgUrl(asset.getAstImgUrl()).ContId(cont.getContId()).
                        ContNumOfType(supplyTypes.get(supplyChar)).ContPeriodType(periodTypes.get(periodChar)).ContSupplyType(userTypes.get(userChar)).build();
                deptAssetResList.add(deptAssetRes);
            }
            else {
                DeptAssetRes deptAssetRes = DeptAssetRes.builder().
                        AssetName(asset.getAstName()).AssetReqStatus(assetRequest.getAstReqStatus()).AssetSpd(asset.getAstSpd()).AssetImgUrl(asset.getAstImgUrl()).ContId(cont.getContId()).
                        ContNumOfType(supplyTypes.get(supplyChar)).ContPeriodType(periodTypes.get(periodChar)).ContSupplyType(userTypes.get(userChar)).build();
                deptAssetResList.add(deptAssetRes);
            }

        }
        return deptAssetResList;
    }
}
