package com.loopcreative.web.file.service;

import com.loopcreative.web.entity.Files;
import com.loopcreative.web.error.RestApiException;
import com.loopcreative.web.error.UserErrorCode;
import com.loopcreative.web.file.repository.FileRepository;
import com.loopcreative.web.util.FileUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.net.URLDecoder;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class FileService {

    private final FileRepository fileRepository;
    private final FileUtil fileUtil;

/*
    public Files save(Files files,Long parentsId,String cd){
        if(files == null){
            throw new RestApiException(UserErrorCode.FAIL_UPLOAD);
        }

        files.setCd(cd);
        Files saveFile = fileRepository.save(files);
        FilesContactNoUpdate(parentsId, saveFile.getId(), cd);
        return saveFile;
    }

 */

    public Files save(Files files,String cd){
        if(files == null){
            throw new RestApiException(UserErrorCode.FAIL_UPLOAD);
        }

        files.setCd(cd);
        Files saveFile = fileRepository.save(files);
        return saveFile;
    }

    public void removeFile(Long Id) {

        Files files = fileRepository.findById(Id)
                .orElseThrow(() -> new RestApiException(UserErrorCode.NO_RESULT));

        boolean removeResult = fileUtil.removeFile(files.getFilePath());

        if(removeResult){
            fileRepository.delete(files);
        }else{
            throw new RestApiException(UserErrorCode.FAIL_FILE_REMOVE);
        }

    }

    public void filesContactIdUpdate( Long parentsId,Long id, String cd){
        String useYn = "Y";
        fileRepository.updateFilesByContactIdAndIdAndCd(parentsId,id,cd,useYn);
    }

    public void filesWorkIdUpdate( Long parentsId,Long id){
        String useYn = "Y";
        fileRepository.updateFilesByWorkIdAndIdAndCd(parentsId,id, useYn);
    }

}
