package com.kbds.itamserveradmin.domain.asset.controller;
import com.kbds.itamserveradmin.domain.asset.service.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AssetController {

    private final AssetService assetService;

    @Autowired
    public AssetController(AssetService assetService) {
        this.assetService = assetService;
    }

    @GetMapping("/d/{corpId}")
    public ResponseEntity<String> PKtest(@PathVariable("corpId")String corpId){

        System.out.println(corpId+"!!!");


        return ResponseEntity.ok( assetService.testSaveForcreatePk(corpId));

    }
}
