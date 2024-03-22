package com.loopcreative.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MailDto  {

    private String fromAddress;
    private List<String> toAddressList = new ArrayList<>();
    private List<String> ccAddressList = new ArrayList<>();
    private List<String> bccAddressList = new ArrayList<>();
    private String subject; // 제목
    private String content; // 메일 내용
    private boolean isUseHtmlYn; // 메일 형식이 HTML인지 여부(true, false)
    private List<AttachFileDto> attachFileList = new ArrayList<>();

}