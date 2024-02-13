package com.kbds.itamserveradmin.global.file;

import com.kbds.itamserveradmin.domain.asset.repository.ManualLogRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {
//
//    private ManualLogRepository manualLogRepository;
//    private MultipartFile multipartFile;
//
//    String originalFilename = multipartFile.getOriginalFilename();
//    String saveFileName = createSaveFileName(originalFilename);
//
//    @Value("${file.dir}")
//    private String uploadPath;
//// 2-1.서버에 파일 저장
//multipartFile.transferTo(new File(getFullPath(saveFileName)));
//
//    // 2-2. DB에 정보 저장
//    String contentType = multipartFile.getContentType();
//
//    FileInfoRegister fileInfoRegister = FileInfoRegister.builder()
//            .fileName(originalFilename)
//            .saveFileName(saveFileName)
//            .contentType(contentType)
//            .deleteFlag(notDeleted).build();
//
//    int fileInfoId = fileInfoDao.insert(fileInfoRegister);
//    public store(){
//
//    }

}
