package com.loopcreative.web.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="tbl_contact")
public class Contact {

    @Id @GeneratedValue
    @Column(name="contact_no")
    private Long id;

    @OneToOne
    @JoinColumn(name = "contact_no")
    private Files files;

    private String contactClientCompany;
    private String contactClientName;
    private String contactClientTel;
    private String contactClientMail;
    private String contactProjectTitle;
    private String contactProjectSchedule;
    private String contactVideoLength;
    private String contactBudget;
    private LocalDate regDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContactClientCompany() {
        return contactClientCompany;
    }

    public void setContactClientCompany(String contactClientCompany) {
        this.contactClientCompany = contactClientCompany;
    }

    public String getContactClientName() {
        return contactClientName;
    }

    public void setContactClientName(String contactClientName) {
        this.contactClientName = contactClientName;
    }

    public String getContactClientTel() {
        return contactClientTel;
    }

    public void setContactClientTel(String contactClientTel) {
        this.contactClientTel = contactClientTel;
    }

    public String getContactClientMail() {
        return contactClientMail;
    }

    public void setContactClientMail(String contactClientMail) {
        this.contactClientMail = contactClientMail;
    }

    public String getContactProjectTitle() {
        return contactProjectTitle;
    }

    public void setContactProjectTitle(String contactProjectTitle) {
        this.contactProjectTitle = contactProjectTitle;
    }

    public String getContactProjectSchedule() {
        return contactProjectSchedule;
    }

    public void setContactProjectSchedule(String contactProjectSchedule) {
        this.contactProjectSchedule = contactProjectSchedule;
    }

    public String getContactVideoLength() {
        return contactVideoLength;
    }

    public void setContactVideoLength(String contactVideoLength) {
        this.contactVideoLength = contactVideoLength;
    }

    public String getContactBudget() {
        return contactBudget;
    }

    public void setContactBudget(String contactBudget) {
        this.contactBudget = contactBudget;
    }

    public LocalDate getRegDate() {
        return regDate;
    }

    public void setRegDate(LocalDate regDate) {
        this.regDate = regDate;
    }

    public void setFiles(Files files) {
        this.files = files;
    }

    public Files getFiles() {
        return files;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", contactClientCompany='" + contactClientCompany + '\'' +
                ", contactClientName='" + contactClientName + '\'' +
                ", contactClientTel='" + contactClientTel + '\'' +
                ", contactClientMail='" + contactClientMail + '\'' +
                ", contactProjectTitle='" + contactProjectTitle + '\'' +
                ", contactProjectSchedule='" + contactProjectSchedule + '\'' +
                ", contactVideoLength='" + contactVideoLength + '\'' +
                ", contactBudget='" + contactBudget + '\'' +
                ", regDate=" + regDate +
                '}';
    }
}
