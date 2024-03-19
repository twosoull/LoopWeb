package com.loopcreative.web.dto;

import com.loopcreative.web.entity.Video;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class VideoDto {
    private Long id;
    private String videoUrl;
    private String videoTitle;
    private String videoContent;
    private String videoType;
    private Integer ord;
    private LocalDateTime regDate;
    private LocalDateTime updDate;

    public VideoDto(Video v) {
        this.id = v.getId();
        this.videoUrl = v.getVideoUrl();
        this.videoTitle = v.getVideoTitle();
        this.videoContent = v.getVideoContent();
        this.videoType = v.getVideoType();
        this.ord = v.getOrd();
        this.regDate = v.getRegDate();
        this.updDate = v.getUpdDate();
    }
}
