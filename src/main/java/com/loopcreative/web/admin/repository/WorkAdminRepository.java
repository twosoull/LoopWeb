package com.loopcreative.web.admin.repository;

import com.loopcreative.web.entity.Work;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkAdminRepository extends JpaRepository<Work,Long> {
}
