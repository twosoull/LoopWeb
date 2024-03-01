package com.loopcreative.web.admin.repository;

import com.loopcreative.web.entity.Contact;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ContactAdminRepository {

    @PersistenceContext
    private EntityManager em;



}
