package com.loopcreative.web.front.repository;

import com.loopcreative.web.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact,Long> {
}
