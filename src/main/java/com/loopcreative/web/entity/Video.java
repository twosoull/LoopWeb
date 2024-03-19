package com.loopcreative.web.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="tbl_video")
public class Video  extends BaseEntity{

    @Id @GeneratedValue
    @Column(name = "video_no")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "work_no")
    private Work work;

    private String videoUrl;
    private String videoTitle;
    private String videoContent;
    private String videoType;
    private Integer ord;

    public Video() {
    }

    public Video(String videoUrl, String videoTitle, String videoContent, String videoType, Integer ord) {
        this.videoUrl = videoUrl;
        this.videoTitle = videoTitle;
        this.videoContent = videoContent;
        this.videoType = videoType;
        this.ord = ord;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Work getWork() {
        return work;
    }

    public void setWork(Work work) {
        this.work = work;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getVideoTitle() {
        return videoTitle;
    }

    public void setVideoTitle(String videoTitle) {
        this.videoTitle = videoTitle;
    }

    public String getVideoContent() {
        return videoContent;
    }

    public void setVideoContent(String videoContent) {
        this.videoContent = videoContent;
    }

    public String getVideoType() {
        return videoType;
    }

    public void setVideoType(String videoType) {
        this.videoType = videoType;
    }

    public Integer getOrd() {
        return ord;
    }

    public void setOrd(Integer ord) {
        this.ord = ord;
    }


}
