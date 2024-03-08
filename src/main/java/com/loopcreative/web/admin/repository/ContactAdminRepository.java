package com.loopcreative.web.admin.repository;

import com.loopcreative.web.entity.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ContactAdminRepository extends JpaRepository<Contact,Long> {

    @Query("select c from Contact c left join fetch c.files")
    Page<Contact> findAllFetchFiles(Pageable pageable);
}
