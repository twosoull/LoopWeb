package com.loopcreative.web.util;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.loopcreative.web.entity.Files;
import com.loopcreative.web.error.RestApiException;
import com.loopcreative.web.error.UserErrorCode;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URLDecoder;
import java.util.UUID;

@Slf4j
@Controller
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


    @Autowired
    private AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

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

            String savePath = saveDir + "/" + saveName;
            String filePath = saveRoot + "/" + saveName;
            log.info("--파일 물리적 저장--");
            log.info("--orgName : {}", orgName);
            log.info("--exName : {}", exName);
            log.info("--saveName : {}", saveName);
            log.info("--savePath : {}", savePath);
            log.info("--filePath : {}", filePath);

            boolean success = Boolean.FALSE;
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

            Files files = getFiles(orgName, exName, saveName, filePath, success);
            return files;
        }else{
            return null;
        }
    }

    private Files getFiles(String orgName, String exName, String saveName, String filePath, boolean success) {
        Files files = null;
        if (success) {
            files = new Files(filePath, orgName, exName, saveName);
        }
        return files;
    }

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

    public void resizeAndSave(byte[] fileData, String savePath, int width, int height) throws IOException {
        // GIF 이미지를 BufferedImage로 변환
        BufferedImage originalImage = ImageIO.read(new ByteArrayInputStream(fileData));

        // BufferedImage를 리사이징
        BufferedImage resizedImage = Thumbnails.of(originalImage)
                .size(width, height)
                .asBufferedImage();

        // 리사이징된 이미지를 GIF로 저장
        ImageIO.write(resizedImage, "jpg", new File(savePath));
    }

    @PostMapping("/fileTest")
    public Files awsUploadFile(MultipartFile file) {
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

            log.info("--AWS S3 저장--");
            log.info("--orgName : {}", orgName);
            log.info("--exName : {}", exName);

            boolean success = Boolean.FALSE;
            try {

                ObjectMetadata metadata= new ObjectMetadata();
                metadata.setContentType(file.getContentType());
                metadata.setContentLength(file.getSize());
                amazonS3Client.putObject(bucket, saveName, file.getInputStream(), metadata);

                success = Boolean.TRUE;
            } catch (IOException e) {
                e.printStackTrace();
                success = Boolean.FALSE;
            }
            String saveUrl = amazonS3Client.getUrl(bucket,saveName).toString();
            log.info("--saveName : {}", saveUrl);
            log.info("--AWS S3 저장 end--");

            Files files = getFiles(orgName, exName, saveName,saveUrl, success);
            return files;
        }else{
            return null;
        }

    }

    public void deleteAwsUploadFile(String fileName){
        log.info("AWS파일 삭제--------------------------------start");
        DeleteObjectRequest request = new DeleteObjectRequest(bucket, fileName);
        amazonS3Client.deleteObject(request);
        log.info("AWS파일 삭제--------------------------------end");
    }

}
