package com.loopcreative.web.admin.repository;

import com.loopcreative.web.entity.Credits;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CreditsAdminRepository extends JpaRepository<Credits,Long> {

}
