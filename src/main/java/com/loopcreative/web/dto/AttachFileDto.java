package com.loopcreative.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AttachFileDto {

    private String realFileNm;
    private String attachFileNm;

    public AttachFileDto(String realFileNm, String attachFileNm) {
        this.realFileNm = realFileNm;
        this.attachFileNm = attachFileNm;
    }
}
