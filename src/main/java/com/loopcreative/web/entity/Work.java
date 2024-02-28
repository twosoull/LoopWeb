package com.loopcreative.web.entity;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="tbl_work")
public class Work {

    @Id @GeneratedValue
    @Column(name="work_no")
    private Long id;

    @OneToMany(mappedBy = "work")
    private List<Files> files = new ArrayList();

    private String workTitle;
    private String workType;
    private String useYn;
    private LocalDateTime regDt;
    private LocalDateTime udpDt;

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

    public List<Files> getFiles() {
        return files;
    }

    public void setFiles(List<Files> files) {
        this.files = files;
    }
}
