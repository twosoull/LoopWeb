package com.loopcreative.web.util;

import com.loopcreative.web.entity.Files;
import com.loopcreative.web.error.RestApiException;
import com.loopcreative.web.error.UserErrorCode;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URLDecoder;
import java.util.UUID;

@Slf4j
@Component
public class FileUtil {

    //back dev
    //private final String RESOURCESDIR = "/Users/iyeonghun/Desktop/PROJECT/LoopCreative/workSpace/loopcreative/src/main";
    //private final String SAVEDIR = "/Users/iyeonghun/Desktop/PROJECT/LoopCreative/workSpace/loopcreative/src/main/resources/static/upload/file";
    //private final String SAVEROOT = "/resources/static/upload/file";

    //front dev
    @Value("${file.server.path}")
    private  String fileServerPath;
    @Value("${file.server.saveDir}")
    private String saveDir = "/Users/iyeonghun/Desktop/PROJECT/LoopCreative/front/front/public/resources/image";
    @Value("${file.server.saveRoot}")
    private  String saveRoot = "/resources/image";


    //final String SAVEDIR = "C:\\loop\\loopMotionStudio\\LoopMotionStudio\\src\\main\\webapp\\resources\\upload\\images";
    //final String SAVEROOT = "/resources/upload/images";

    /**
     * 1. 물리적 파일 업로드
     * 2. 로직이 성공하면 Files 객체 안에 org파일이름,저장파일이름,확장자,파일경로 정보를 넣어 반환한다.
     * @param file
     * @return
     */
    public Files uploadFile(MultipartFile file) {
        Boolean hasFile = file != null && !file.isEmpty() ? Boolean.TRUE : Boolean.FALSE;
        if(!hasFile){return null;}

        hasFile = file.getOriginalFilename() != null ? Boolean.TRUE : Boolean.FALSE;
        if (!hasFile) {return null;}

        if(hasFile) {

            // 오리지널 파일이름
            String orgName = file.getOriginalFilename();
            orgName = orgName.replaceAll(" ", "");
            // 확장자
            String exName = orgName.substring(orgName.lastIndexOf("."));
            // 저장명
            String saveName = System.currentTimeMillis() + UUID.randomUUID().toString() + exName;

            long fileSize = file.getSize();

            String savePath = saveDir + "/" + saveName;
            String filePath = saveRoot + "/" + saveName;
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

    /**
     * 1. 파일 다운로드
     * 2. 파일저장이름으로 파일을 다운로드한다
     * @param fileName
     * @param response
     */
    /*
    public void downloadFile(String fileName , HttpServletResponse response){
        File f = new File(SAVEDIR + "/", fileName);
        try {
            // file 다운로드 설정
            response.setContentType("application/download");
            response.setContentLength((int)f.length());
            response.setHeader("Content-disposition", "attachment;filename=\"" + fileName + "\"");
            // response 객체를 통해서 서버로부터 파일 다운로드
            OutputStream os = null;
            os = response.getOutputStream();
            // 파일 입력 객체 생성
            FileInputStream fis = new FileInputStream(f);
            FileCopyUtils.copy(fis, os);
            fis.close();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RestApiException(UserErrorCode.FAIL_FILE_DOWNLOAD);
        }
    }
    */
    public void downloadFile(String fileName , HttpServletResponse response){
        File f = new File(saveDir + "/", fileName);
        try {
            // file 다운로드 설정
            response.setContentType("application/download");
            response.setContentLength((int)f.length());
            response.setHeader("Content-disposition", "attachment;filename=\"" + fileName + "\"");
            response.setHeader("Content-Transfer-Encoding", "binary");
            response.setHeader( "Access-Control-Expose-Headers","Content-Disposition");

            byte[] fileByte =  java.nio.file.Files.readAllBytes( new File(saveDir + "/", fileName).toPath());
            response.getOutputStream().write(fileByte);
            response.getOutputStream().flush();
            response.getOutputStream().close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RestApiException(UserErrorCode.FAIL_FILE_DOWNLOAD);
        }
    }


    /**
     * 1. 물리적인 파일삭제이며 완료는 True 실패는 False 반환
     * @param filePath
     * @return
     */
    public boolean removeFile(String filePath)  {

        File file = null;
        try {
            file = new File(URLDecoder.decode(fileServerPath + filePath, "UTF-8")); // 풀패스 필요
        } catch (UnsupportedEncodingException e) {
            throw new RestApiException(UserErrorCode.FAIL_FILE_REMOVE);
        }
        log.info("파일 삭제--------------------------------start");
        log.info("파일 유무 : " + String.valueOf(file.exists()));
        boolean result = file.delete();
        log.info("파일 삭제 여부 : " + String.valueOf(result));
        log.info("파일 삭제----------------------------------end");

        return result;
    }
}
