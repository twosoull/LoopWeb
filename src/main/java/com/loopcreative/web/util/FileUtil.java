package com.loopcreative.web.util;

import com.loopcreative.web.entity.Files;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.UUID;

@Slf4j
@Component
public class FileUtil {

    private final String SAVEDIR = "/Users/iyeonghun/Desktop/PROJECT/LoopCreative/workSpace/loopcreative/src/main/resources/static/upload/file";
    private final String SAVEROOT = "/resources/static/upload/file";
    //final String SAVEDIR = "C:\\loop\\loopMotionStudio\\LoopMotionStudio\\src\\main\\webapp\\resources\\upload\\images";
    //final String SAVEROOT = "/resources/upload/images";

    public Files uploadFile(MultipartFile file) {
        Boolean hasFile = file.getSize() < 0 ? Boolean.TRUE : Boolean.FALSE;

        if(hasFile) {

            // 오리지널 파일이름
            String orgName = file.getOriginalFilename();
            orgName = orgName.replaceAll(" ", "");
            // 확장자
            String exName = orgName.substring(orgName.lastIndexOf("."));
            // 저장명
            String saveName = System.currentTimeMillis() + UUID.randomUUID().toString() + exName;

            long fileSize = file.getSize();

            String savePath = SAVEDIR + "/" + saveName;
            String filePath = SAVEROOT + "/" + saveName;
            log.info("--파일 물리적 저장--");
            log.info("--orgName : {}", orgName);
            log.info("--exName : {}", exName);
            log.info("--saveName : {}", saveName);
            log.info("--savePath : {}", savePath);
            log.info("--filePath : {}", filePath);


            boolean success = Boolean.FALSE;
            boolean fail = !success;
            try {
                byte[] fileData = file.getBytes();

                OutputStream out = new FileOutputStream(savePath); // 어떤이름으로 저장하는지 까지도..
                BufferedOutputStream bos = new BufferedOutputStream(out);
                bos.write(fileData);
                bos.close();
                out.close();
                success = Boolean.TRUE;
            } catch (Exception e) {
                e.printStackTrace();
                log.info("--파일 물리적 저장 실패 -");
                success = Boolean.FALSE;
            } finally {

            }
            log.info("--파일 물리적 저장 end--");

            Files files = null;
            if (success) {
                files = new Files(filePath, orgName, exName, saveName);
            }
            return files;
        }else{
            return null;
        }
    }

/*
    public FileVo removeFile(String saveName) throws IOException {
        Path filePath = Paths.get(SAVEDIR+"\\"+saveName);
        Files.delete(filePath);
        return null;
    }
*/
}
