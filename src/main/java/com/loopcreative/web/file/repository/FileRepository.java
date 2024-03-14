package com.loopcreative.web.file.repository;

import com.loopcreative.web.entity.Files;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FileRepository extends JpaRepository<Files,Long> {

    @Query(value = "update tbl_file f set f.contact_no = :contact_no where f.file_no = :file_no and f.cd = :cd", nativeQuery = true)
    void updateFilesByContactIdAndIdAndCd(@Param("contact_no") Long updateId,
                                         @Param("file_no") Long file_id,
                                         @Param("cd") String cd);

    @Query(value = "update tbl_file f set f.work_no = :work_no where f.file_no = :file_no ", nativeQuery = true)
    void updateFilesByWorkIdAndIdAndCd(@Param("work_no") Long updateId,
                                          @Param("file_no") Long file_id);
}
