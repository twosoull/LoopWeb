package com.loopcreative.web.form;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class FileForm {

    private Long id;
    private String filePath;
    private String orgName;
    private String exName;
    private String saveName;
    private String cd;
    private Integer ord; //전체순서
    private Integer tmplType; //템플릿 순서
    private Integer picOrd; //사진 순서
    private MultipartFile multipartFile;
    private Long videoId;
}
