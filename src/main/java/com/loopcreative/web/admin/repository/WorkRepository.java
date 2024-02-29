package com.loopcreative.web.admin.repository;


import com.loopcreative.web.entity.Work;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class WorkRepository {

    @PersistenceContext
    private EntityManager em;

    public List<Work> findAll() {
       return em.createQuery("select w from Work w",Work.class).getResultList();
    }
}
