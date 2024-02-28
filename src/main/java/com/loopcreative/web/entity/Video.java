package com.loopcreative.web.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="tbl_video")
public class Video {

    @Id @GeneratedValue
    @Column(name = "video_no")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "work_no")
    private Work work;

    private String videoUrl;
    private String videoTitle;
    private String videoContent;
    private String videoType;
    private Integer ord;
    private LocalDateTime regDt;
    private LocalDateTime udpDt;

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

    public LocalDateTime getRegDt() {
        return regDt;
    }

    public void setRegDt(LocalDateTime regDt) {
        this.regDt = regDt;
    }

    public LocalDateTime getUdpDt() {
        return udpDt;
    }

    public void setUdpDt(LocalDateTime udpDt) {
        this.udpDt = udpDt;
    }

    @Override
    public String toString() {
        return "Video{" +
                "id=" + id +
                ", work=" + work +
                ", videoUrl='" + videoUrl + '\'' +
                ", videoTitle='" + videoTitle + '\'' +
                ", videoContent='" + videoContent + '\'' +
                ", videoType='" + videoType + '\'' +
                ", ord=" + ord +
                ", regDt=" + regDt +
                ", udpDt=" + udpDt +
                '}';
    }
}
