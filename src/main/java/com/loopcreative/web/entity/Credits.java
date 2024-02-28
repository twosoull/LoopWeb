package com.loopcreative.web.entity;

import jakarta.persistence.*;

@Entity
@Table(name="tbl_credits")
public class Credits {

    @Id @GeneratedValue
    @Column(name = "credits_no")
    private Long id;

    @ManyToOne
    @JoinColumn(name="work_no")
    private Work work;

    private String job;
    private String name;
    private Integer ord;

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

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getOrd() {
        return ord;
    }

    public void setOrd(Integer ord) {
        this.ord = ord;
    }

    @Override
    public String toString() {
        return "Credits{" +
                "id=" + id +
                ", work=" + work +
                ", job='" + job + '\'' +
                ", name='" + name + '\'' +
                ", ord=" + ord +
                '}';
    }
}
