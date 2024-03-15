package com.loopcreative.web.admin.repository;

import com.loopcreative.web.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoAdminRepository extends JpaRepository<Video,Long> {
}
