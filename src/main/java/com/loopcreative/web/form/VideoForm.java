package com.loopcreative.web.form;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class VideoForm {
    private Long[] videoId;
    private String[] videoUrl;
    private String[] videoTitle;
    private String[] videoContent;
    private String[] videoType;
    private Integer[] ord;
    private LocalDateTime regDt;
    private LocalDateTime udpDt;
}
