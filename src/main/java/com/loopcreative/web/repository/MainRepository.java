package com.loopcreative.web.repository;

import com.loopcreative.web.entity.Work;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MainRepository {

    @PersistenceContext
    private EntityManager em;

    public List<Work> findWorks(){
        return em.createQuery("select w from Work w order by w.regDt desc", Work.class).setMaxResults(15).getResultList();
    }
}
