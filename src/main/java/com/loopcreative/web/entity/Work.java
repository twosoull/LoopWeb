package com.loopcreative.web.entity;

import com.loopcreative.web.dto.CreditsDto;
import com.loopcreative.web.dto.FilesDto;
import com.loopcreative.web.dto.VideoDto;
import com.loopcreative.web.dto.WorkDto;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name="tbl_work")
public class Work {

    @Id @GeneratedValue
    @Column(name="work_no")
    private Long id;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "work_no")
    private List<Files> files;

    @OneToMany(mappedBy = "work" ,fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Credits> credits = new ArrayList<>();

    @OneToMany(mappedBy = "work",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Video> videos = new ArrayList<>();

    private String workTitle;
    private String workType;
    private String useYn;
    private LocalDateTime regDate;
    private LocalDateTime updDate;


    public List<Credits> getCredits() {
        return credits;
    }

    public void setCredits(List<Credits> credits) {
        this.credits = credits;
    }

    public List<Files> getFiles() {
        return files;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWorkTitle() {
        return workTitle;
    }

    public void setWorkTitle(String workTitle) {
        this.workTitle = workTitle;
    }

    public String getWorkType() {
        return workType;
    }

    public void setWorkType(String workType) {
        this.workType = workType;
    }

    public String getUseYn() {
        return useYn;
    }

    public void setUseYn(String useYn) {
        this.useYn = useYn;
    }

    public LocalDateTime getRegDate() {
        return regDate;
    }

    public LocalDateTime getUpdDate() {
        return updDate;
    }

    public List<Video> getVideos() {
        return videos;
    }

    public void setVideos(List<Video> videos) {
        this.videos = videos;
    }

    public WorkDto changeDto(){
        WorkDto wd = new WorkDto();
        wd.setId(this.getId());
        wd.setWorkTitle(this.getWorkTitle());
        wd.setWorkType(this.getWorkType());
        wd.setUseYn(this.getUseYn());
        wd.setRegDt(this.getRegDate());
        wd.setUdpDt(this.getUpdDate());
        if(this.getFiles() != null){
            wd.setFilesList(this.getFiles().stream().map(f -> new FilesDto(f)).collect(Collectors.toList()));
        }
        if(this.getCredits() != null){
            wd.setCredits(this.getCredits().stream().map(c -> new CreditsDto(c)).collect(Collectors.toList()));
        }
        if(this.getVideos() !=null){
            wd.setVideos(this.getVideos().stream().map(v -> new VideoDto(v)).collect(Collectors.toList()));
        }
        return wd;
    }

    public void addCredits(Credits c){
        credits.add(c);
        c.setWork(this);
    }

    public void addVideo(Video v){
        videos.add(v);
        v.setWork(this);
    }
}
