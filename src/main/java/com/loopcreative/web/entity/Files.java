package com.loopcreative.web.entity;

import jakarta.persistence.*;

@Entity
@Table(name="tbl_file")
public class Files {

    @Id @GeneratedValue
    @Column(name="file_no")
    private Long Id;

    @ManyToOne
    @JoinColumn(name="work_no")
    private Work work;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="contact_no")
    private Contact contact;

    private Integer ord;
    private String filePath;
    private String orgName;
    private String exName;
    private String saveName;
    private String cd;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Integer getOrd() {
        return ord;
    }

    public void setOrd(Integer ord) {
        this.ord = ord;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getExName() {
        return exName;
    }

    public void setExName(String exName) {
        this.exName = exName;
    }

    public String getSaveName() {
        return saveName;
    }

    public void setSaveName(String saveName) {
        this.saveName = saveName;
    }

    public String getCd() {
        return cd;
    }

    public void setCd(String cd) {
        this.cd = cd;
    }

    public Work getWork() {
        return work;
    }

    public void setWork(Work work) {
        this.work = work;
    }

    @Override
    public String toString() {
        return "Files{" +
                "Id=" + Id +
                ", ord=" + ord +
                ", filePath='" + filePath + '\'' +
                ", orgName='" + orgName + '\'' +
                ", exName='" + exName + '\'' +
                ", saveName='" + saveName + '\'' +
                ", cd='" + cd + '\'' +
                '}';
    }
}
