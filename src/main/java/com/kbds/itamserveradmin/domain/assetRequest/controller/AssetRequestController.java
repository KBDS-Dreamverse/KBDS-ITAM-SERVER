package com.kbds.itamserveradmin.domain.assetRequest.controller;


import com.kbds.itamserveradmin.domain.assetRequest.dto.*;
import com.kbds.itamserveradmin.domain.assetRequest.entity.AssetRequest;
import com.kbds.itamserveradmin.domain.assetRequest.service.AssetRequestService;
import com.kbds.itamserveradmin.domain.user.dto.AssetAdminList;
import com.kbds.itamserveradmin.domain.user.dto.AssetAdminListRes;
import com.kbds.itamserveradmin.global.exception.BaseException;
import com.kbds.itamserveradmin.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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


    @GetMapping("/kbitam/mypage/{userId}/requestList/search")
    @ResponseBody
    public ResponseEntity<Page<AstReqSearchRes>> userRequestSearch(@PathVariable String userId,
                                                            @RequestParam(name = "start", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime start,
                                                            @RequestParam(name = "end", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime end,
                                                            @PageableDefault(size=5) Pageable pageable){

        System.out.println("시작 : "+userId);
        AstReqSearchReq req = new AstReqSearchReq(userId,start,end);

        Page<AstReqSearchRes> t = assetRequestService.userRequestSearch(req, pageable);
        System.out.println("빠이");
        return ResponseEntity.ok(t);

    }

    @GetMapping("/kk/{userId}")
    @ResponseBody
    public ResponseEntity<String> test(@PathVariable String userId){

        System.out.println("시작 : "+userId);
        String t = assetRequestService.test(userId);
        System.out.println("빠이");
        return ResponseEntity.ok(t);

    }


    @GetMapping("/kbitam/mypage/{userId}/requestList/{astReqId}")
    public ResponseEntity<AstReqDetailRes> getUserRequestDetails(@PathVariable  String userId, @PathVariable String astReqId){

        AstReqDetailRes res = assetRequestService.getAstReqDetatils(astReqId,userId);

        return ResponseEntity.ok(res);


    }
}
