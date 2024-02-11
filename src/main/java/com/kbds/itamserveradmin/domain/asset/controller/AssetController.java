package com.kbds.itamserveradmin.domain.asset.controller;
import com.kbds.itamserveradmin.domain.asset.dto.AssetRes;
import com.kbds.itamserveradmin.domain.asset.dto.ManualLogRes;
import com.kbds.itamserveradmin.domain.asset.service.AssetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/kbitam")
public class AssetController {

    public final AssetService assetService;

    @GetMapping("/{dept}/{cont-id}/info")
    public ResponseEntity<AssetRes> info(@PathVariable("cont-id") String contId, @PathVariable String dept){
        AssetRes assetRes = assetService.getInfo(contId);
        if (assetRes == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(assetRes);
    }

    @GetMapping("/{dept}/{cont-id}/installguide")
    public ResponseEntity<ManualLogRes> installGuide(@PathVariable("cont-id") String contId, @PathVariable String dept){
        ManualLogRes installGuideRes = assetService.getInstallGuide(contId);
        if(installGuideRes == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(installGuideRes);
    }
}
