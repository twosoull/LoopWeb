package com.loopcreative.web.file;

import com.loopcreative.web.entity.Files;
import com.loopcreative.web.file.service.FileService;
import com.loopcreative.web.form.FileForm;
import com.loopcreative.web.util.FileUtil;
import com.loopcreative.web.util.Message;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@Slf4j
public class FileController {

    private final FileUtil fileUtil;
    private final FileService fileService;

    /**
     * 1. 파일 업로드이며 연관관계 설정 이전에 사용된다.
     *    (parent_no는 각각 엔티티(Work, Contact)에서 생성시 업데이트 된다.
     * 2. UseYn는 "N"으로 설정되어 있으며, 부모 엔티티가 저장되지 않을 경우(파일저장) 새벽 2시 자동 삭제 된다.
     * @param fileForm
     * @return
     */
    @PostMapping("/fileUpload")
    public ResponseEntity<Message> insertFile(FileForm fileForm){

        Files files = fileUtil.uploadFile(fileForm.getMultipartFile());
        files.setCd(fileForm.getCd());
        files.setOrd(fileForm.getOrd());
        files.setPicOrd(fileForm.getPicOrd());
        files.setTmplType(fileForm.getTmplType());
        files.setUseYn("N");
        Files saveFile = fileService.save(files,fileForm.getCd());

        Message message = new Message();
        message.setMessage("저장에 성공했습니다.");
        message.setData(saveFile.getId());
        return new ResponseEntity<Message>(message, HttpStatus.OK);
    }

    /**
     * 1. 물리적 파일 삭제, db 데이터 삭제
     * @param fileId
     * @return
     */
    @PostMapping("/fileRemove")
    public ResponseEntity<Message> removeFile(@RequestParam("fileId")Long fileId){
        fileService.removeFile(fileId);

        return new ResponseEntity<Message>(new Message(),HttpStatus.OK);
    }

    /**
     * 1. 파일 다운로드
     * @param fileName
     * @param response
     */
    @PostMapping("/fileDownload")
    public void downloadFile(@RequestParam("fileName")String fileName, HttpServletResponse response){
        fileUtil.downloadFile(fileName,response);

    }
}
