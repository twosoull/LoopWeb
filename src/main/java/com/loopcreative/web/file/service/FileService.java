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

    /**
     * 1. 파일 저장
     * @param files
     * @param cd
     * @return
     */
    public Files save(Files files,String cd){
        if(files == null){
            throw new RestApiException(UserErrorCode.FAIL_UPLOAD);
        }

        files.setCd(cd);
        Files saveFile = fileRepository.save(files);
        return saveFile;
    }

    /**
     * 1. 조회 후 파일 삭제
     * @param Id
     */
    public void removeFile(Long Id) {
        Files files = fileRepository.findById(Id)
                .orElseThrow(() -> new RestApiException(UserErrorCode.NO_RESULT));

        if(".gif".equals(files.getExName())){
            awsDeleteFileAndData(files);
        }else{
            deleteFileAndData(files);
        }
    }

    /**
     * 1. 스케줄러에서 사용
     * 2. 사용하지 않는 파일은 조회 후 삭제한다.
     * 3. 만약 물리적 경로에 파일을 찾을 수 없는 경우 삭제 하지 않는다.(db)
     */
    public void removeFileUseYnN(){
        List<Files> filesList = fileRepository.findByUseYn("N");

        for (Files files : filesList) {
            if(".gif".equals(files.getExName())){
                awsDeleteFileAndData(files);
            }else{
                deleteFileAndData(files);
            }
        }

    }
    /**
     * 1. 물리 파일과 db 데이터 삭제
     * @param files
     */
    private void deleteFileAndData(Files files) {
        boolean removeResult = fileUtil.removeFile(files.getFilePath());

        if(removeResult){
            fileRepository.delete(files);
        }
    }
    private void awsDeleteFileAndData(Files files) {
        fileUtil.deleteAwsUploadFile(files.getSaveName());
        fileRepository.delete(files);
    }
    /**
     * 1. ContactId를 부모키로 수정
     * 2. useYn은 "Y"로 부모키가 있고, 사용 된다는 것으로 수정
     * @param parentsId
     * @param id
     * @param cd
     */
    public void filesContactIdUpdate( Long parentsId,Long id, String cd){
        String useYn = "Y";
        fileRepository.updateFilesByContactIdAndIdAndCd(parentsId,id,cd,useYn);
    }

    /**
     * 1. WorkId를 부모키로 수정
     * 2. useYn은 "Y"로 부모키가 있고, 사용 된다는 것으로 수정
     * @param parentsId
     * @param id
     */
    public void filesWorkIdUpdate( Long parentsId,Long id, Long videoId,Integer tmplType,Integer ord,Integer picOrd){
        String useYn = "Y";
        fileRepository.updateFilesByWorkIdAndIdAndCd(parentsId,id,videoId,
                useYn,tmplType,ord,picOrd);
    }

    public void filesThumbnailWorkIdUpdate( Long parentsId,Long id, String cd) {
        String useYn = "Y";
        fileRepository.updateFilesThumbnailByWorkIdAndIdAndCd(parentsId,id, useYn,cd);
    }

    public void deleteById(Long removeFileId){
        Files files = fileRepository.findById(removeFileId).orElseThrow(() -> new RestApiException(UserErrorCode.FAIL_REMOVE_FILE_NO_RESULT));
        deleteFileAndData(files);
        /*
        if(".gif".equals(files.getExName())){
            awsDeleteFileAndData(files);
        }else{
            deleteFileAndData(files);
        }
        */
    }

}
