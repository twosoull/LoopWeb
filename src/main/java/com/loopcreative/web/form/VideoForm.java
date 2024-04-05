package com.loopcreative.web.form;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class VideoForm {
    /*
    private Long[] videoId;
    private String[] videoUrl;
    private String[] videoTitle;
    private String[] videoContent;
    private String[] videoType;
    private Integer[] videoOrd;
    private LocalDateTime regDt;
    private LocalDateTime udpDt;
    */
    private Long id;
    private Long videoId;
    private String videoUrl;
    private String videoTitle;
    private String videoContent;
    private String videoType;
    private Integer videoOrd;
    private LocalDateTime regDt;
    private LocalDateTime udpDt;
    private List<VideoForm> videoForms;


}
