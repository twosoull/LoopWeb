package com.loopcreative.web.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.time.LocalDateTime;

@MappedSuperclass
public class BaseEntity {

    @Column(updatable = false) //생성일은 수정할 일이 없기 때문에 막아준다.
    private LocalDateTime regDate;
    private LocalDateTime updDate;

    public LocalDateTime getRegDate() {
        return regDate;
    }

    public void setRegDate(LocalDateTime regDate) {
        this.regDate = regDate;
    }

    public LocalDateTime getUpdDate() {
        return updDate;
    }

    public void setUpdDate(LocalDateTime updDate) {
        this.updDate = updDate;
    }

    @PrePersist
    public void prePersist(){
        LocalDateTime now = LocalDateTime.now();
        regDate = now;
        updDate = now; //업데이트에도 넣는 이유는 updateDate가 비어져 있으면 후에 쿼리를 날릴 때에 null값으로 인해 지저분해진다.
    }

    @PreUpdate
    public void preUpdate(){
        updDate = LocalDateTime.now();
    }
}
