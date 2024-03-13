package com.loopcreative.web.entity;

import jakarta.persistence.*;

@Entity
@Table(name="tbl_file")
public class Files {

    @Id @GeneratedValue
    @Column(name="file_no")
    private Long Id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contact_no")
    private Contact contact;

    private Integer ord; //전체순서
    private String filePath;
    private String orgName;
    private String exName;
    private String saveName;
    private String cd;
    private Integer tmplType; //템플릿 순서
    private Integer picOrd; //사진 순서

    public Files() {
    }

    public Files(String filePath, String orgName, String exName, String saveName) {
        this.filePath = filePath;
        this.orgName = orgName;
        this.exName = exName;
        this.saveName = saveName;
    }

    public Files(String filePath, String orgName, String exName, String saveName, String cd) {
        this.filePath = filePath;
        this.orgName = orgName;
        this.exName = exName;
        this.saveName = saveName;
        this.cd = cd;
    }

    public Files(Integer ord, Integer picOrd, Integer tmplType) {
        this.ord = ord;
        this.tmplType = tmplType;
        this.picOrd = picOrd;
    }

    public Integer getTmplType() {
        return tmplType;
    }

    public void setTmplType(Integer tmplType) {
        this.tmplType = tmplType;
    }

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

    public Integer getPicOrd() {
        return picOrd;
    }

    public void setPicOrd(Integer picOrd) {
        this.picOrd = picOrd;
    }
}
