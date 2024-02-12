package com.kbds.itamserveradmin.domain.asset.controller;
import com.kbds.itamserveradmin.domain.asset.dto.AssetRes;
import com.kbds.itamserveradmin.domain.asset.dto.ManualLogRes;
import com.kbds.itamserveradmin.domain.asset.service.AssetService;
import com.kbds.itamserveradmin.domain.asset.service.ManualLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;


@RestController
@RequiredArgsConstructor
@RequestMapping("/kbitam")
public class AssetController {

    public final AssetService assetService;
    public final ManualLogService manualLogService;

    @GetMapping("/{dept}/{cont-id}/info")
    public ResponseEntity<AssetRes> info(@PathVariable("cont-id") String contId, @PathVariable String dept){
        AssetRes assetRes = assetService.getInfo(contId);
        if (assetRes == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(assetRes);
    }

    @GetMapping("/{dept}/{cont-id}/installguide")
    public ResponseEntity<byte[]> installGuide(@PathVariable("cont-id") String contId, @PathVariable String dept) throws IOException {
            ManualLogRes installGuideRes = assetService.getInstallGuide(contId);
            if (installGuideRes == null) {
                return ResponseEntity.notFound().build();
            }

            String pdfFilePath = installGuideRes.getMnLogInstallGuide();

            // PDF 파일을 HTML로 변환
            ByteArrayOutputStream htmlStream = new ByteArrayOutputStream();
            PdfReader reader = new PdfReader(pdfFilePath);
            PdfDocument pdfDoc = new PdfDocument(reader);
            pdfDoc.copyPagesTo(1, pdfDoc.getNumberOfPages(), new PdfDocument(new com.itextpdf.kernel.pdf.PdfWriter(htmlStream)));
            pdfDoc.close();
            reader.close();
            String htmlContent = new String(htmlStream.toByteArray());

            // HTML을 바이트 배열로 변환하여 클라이언트에게 전송
            byte[] htmlBytes = htmlContent.getBytes();

            // HTTP 응답으로 HTML 파일 반환
            return ResponseEntity.ok()
                    .contentType(MediaType.TEXT_HTML)
                    .body(htmlBytes);

        }
    }
//    @GetMapping("/{dept}/{cont-id}/pdfView")
//    public ModelAndView getPdfView(@PathVariable("cont-id") String contId, @PathVariable String dept) {
//        findBycontId
//        ManualLog manualLog = manualLogService.findByMnLogId(mnLogId);
//        if (manualLog == null) {
//            return null;
//        }
//
//        ModelAndView mav = new ModelAndView();
//        mav.setView(new CustomPdfView());
//        mav.addObject("manualLog", manualLog);
//        return mav;
//    }

