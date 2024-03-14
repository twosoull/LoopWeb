package com.loopcreative.web.file;

import com.loopcreative.web.entity.Files;
import com.loopcreative.web.file.service.FileService;
import com.loopcreative.web.form.FileForm;
import com.loopcreative.web.util.FileUtil;
import com.loopcreative.web.util.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@Slf4j
public class FileController {

    private final FileUtil fileUtil;
    private final FileService fileService;

    /**
     *
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
        Files saveFile = fileService.save(files,fileForm.getCd());

        Message message = new Message();
        message.setMessage("저장에 성공했습니다.");
        message.setData(saveFile.getId());
        return new ResponseEntity<Message>(message, HttpStatus.OK);
    }
}
