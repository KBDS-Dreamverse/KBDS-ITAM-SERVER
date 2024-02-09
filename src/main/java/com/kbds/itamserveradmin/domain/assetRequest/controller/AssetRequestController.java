package com.kbds.itamserveradmin.domain.assetRequest.controller;


import com.kbds.itamserveradmin.domain.assetRequest.dto.AssetRequestReq;
import com.kbds.itamserveradmin.domain.assetRequest.entity.AssetRequest;
import com.kbds.itamserveradmin.domain.assetRequest.service.AssetRequestService;
import com.kbds.itamserveradmin.domain.user.dto.AssetAdminList;
import com.kbds.itamserveradmin.domain.user.dto.AssetAdminListRes;
import com.kbds.itamserveradmin.global.exception.BaseException;
import com.kbds.itamserveradmin.global.exception.ErrorCode;
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
    public ResponseEntity<String> requestAsset(@PathVariable String dept,
                                               @PathVariable String contId,
                                               @RequestBody AssetRequestReq req){

//        System.out.println("dept :"+dept+"  conId:"+contId);
//        System.out.println(req.getContId());
//        System.out.println(req.getAstReqStartDate());
//        System.out.println(req.getAstReqEndDate());
//        System.out.println(req.getAstReqEndDate());
//        System.out.println(req.getAstReqMgDate());
//        System.out.println(req.getAstReqReason());
//        System.out.println(req.getUserId());

        assetRequestService.RequestAsset(contId,req);


        return ResponseEntity.ok("성공");


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
