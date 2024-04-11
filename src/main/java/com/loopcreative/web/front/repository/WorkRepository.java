package com.loopcreative.web.front.repository;

import com.loopcreative.web.entity.Work;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface WorkRepository extends JpaRepository<Work,Long> {

    @Query("SELECT w FROM Work w JOIN FETCH w.files f " +
            "WHERE w.id = :id ORDER BY CASE WHEN f.cd = 'thumb_nail' THEN 0 ELSE 1 END, f.ord ASC, f.tmplType ASC, f.picOrd ASC")
    Optional<Work> findWorkFileById(@Param("id")Long id);

    List<Work> findFirst20ByOrderByRegDateDesc();
    List<Work> findAllByUseYnOrderByRegDateDesc(@Param("useYn") String UseYn);
}
