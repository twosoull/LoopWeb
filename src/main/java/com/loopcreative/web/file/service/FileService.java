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

import java.util.List;

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

    public void filesContactIdUpdate( Long parentsId,Long id, String cd){
        fileRepository.updateFilesByContactIdAndIdAndCd(parentsId,id,cd);
    }

    public void filesWorkIdUpdate( Long parentsId,Long id){
        fileRepository.updateFilesByWorkIdAndIdAndCd(parentsId,id);
    }
}