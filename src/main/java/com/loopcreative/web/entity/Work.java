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

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "work_no")
    private List<Files> files;

    private String workTitle;
    private String workType;
    private String useYn;


    private LocalDateTime regDate;
    private LocalDateTime updDate;






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

}
