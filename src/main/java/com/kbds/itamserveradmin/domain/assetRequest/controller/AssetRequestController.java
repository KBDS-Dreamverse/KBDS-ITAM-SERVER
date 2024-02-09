package com.kbds.itamserveradmin.domain.assetRequest.controller;


import com.kbds.itamserveradmin.domain.assetRequest.dto.AssetRequestReq;
import com.kbds.itamserveradmin.domain.assetRequest.dto.AssetRequestRes;
import com.kbds.itamserveradmin.domain.assetRequest.entity.AssetRequest;
import com.kbds.itamserveradmin.domain.assetRequest.service.AssetRequestService;
import com.kbds.itamserveradmin.domain.user.dto.AssetAdminList;
import com.kbds.itamserveradmin.domain.user.dto.AssetAdminListRes;
import com.kbds.itamserveradmin.global.exception.BaseException;
import com.kbds.itamserveradmin.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AssetRequestController {


    private final AssetRequestService assetRequestService;

    @Autowired
    public AssetRequestController(AssetRequestService assetRequestService) {
        this.assetRequestService = assetRequestService;
    }


    @PostMapping("/test")
    @ResponseBody
    public ResponseEntity<String> test(){
        boolean test = true;
        System.out.println("시작");

        if(test) {
            throw new BaseException(ErrorCode.INTERNAL_SERVER_ERROR);
        }

        return ResponseEntity.ok("성공");

    }


    @PostMapping("/kbitam/{dept}/{contId}/request")
    @ResponseBody
    public ResponseEntity<AssetRequestRes> requestAsset(@PathVariable String dept,
                                                        @PathVariable String contId,
                                                        @RequestBody AssetRequestReq req){


        AssetRequestRes as = assetRequestService.RequestAsset(dept,contId,req);

        return ResponseEntity.ok(as);


    }

    @GetMapping("/kbitam/getassetadminList/{conId}")
    @ResponseBody
    public ResponseEntity<AssetAdminListRes> getAssetAdminList(@PathVariable String conId){
//        AssetAdminListRes result = assetRequestService.getAssetAdminList(conId);
//        System.out.println("hello1");
//        System.out.println(result.getCount() + result.getData().toString());
//        System.out.println("hello2");
        return ResponseEntity.ok(assetRequestService.getAssetAdminList(conId));
    }
}
