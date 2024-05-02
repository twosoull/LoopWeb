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

    @Query("SELECT w FROM Work w JOIN FETCH w.files f WHERE w.useYn=:useYn And f.cd = :cd ORDER BY w.id DESC LIMIT 20")
    List<Work> findWorkFilesFirst20ByUseYnAndFilesCdOrderByIdDesc(@Param("useYn") String useYn,@Param("cd") String cd);

    @Query("SELECT w FROM Work w JOIN FETCH w.files f WHERE w.useYn=:useYn And f.cd = :cd ORDER BY w.id")
    List<Work> findAllByUseYnAndFilesCdOrderByIdDesc(@Param("useYn") String useYn,@Param("cd") String cd);
}
