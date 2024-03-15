package com.loopcreative.web.dto;

import com.loopcreative.web.entity.Files;
import lombok.Data;

@Data
public class FilesDto {

    private Long Id;
    private Integer ord; //전체순서
    private String filePath;
    private String orgName;
    private String exName;
    private String saveName;
    private String cd;
    private Integer tmplType; //템플릿 순서
    private Integer picOrd; //사진 순서

    public FilesDto(Files f) {
        this.Id = f.getId();
        this.ord = f.getOrd();
        this.filePath = f.getFilePath();
        this.orgName = f.getOrgName();
        this.exName = f.getExName();
        this.saveName = f.getSaveName();
        this.cd = f.getCd();
        this.tmplType = f.getTmplType();
        this.picOrd = f.getPicOrd();
    }
}
