package com.loopcreative.web.file.repository;

import com.loopcreative.web.entity.Files;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FileRepository extends JpaRepository<Files,Long> {

    @Query(value = "update tbl_file f set f.contact_no = :contact_no , f.use_yn = :use_yn where f.file_no = :file_no and f.cd = :cd", nativeQuery = true)
    void updateFilesByContactIdAndIdAndCd(@Param("contact_no") Long updateId,
                                         @Param("file_no") Long file_id,
                                         @Param("cd") String cd,
                                          @Param("use_yn") String use_yn);

    @Query(value = "            update tbl_file f set f.work_no = :work_no, f.video_no = :video_no , f.use_yn = :use_yn,f.tmpl_type =:tmpl_type, f.ORD =:ord ,f.pic_ord =:pic_ord where f.file_no = :file_no  ", nativeQuery = true)
    void updateFilesByWorkIdAndIdAndCd(@Param("work_no") Long updateId,
                                       @Param("file_no") Long file_id,
                                       @Param("video_no") Long video_id,
                                       @Param("use_yn") String use_yn,
                                       @Param("tmpl_type")int tmpl_type,
                                       @Param("ord")int ord,
                                       @Param("pic_ord")int pic_ord);

    @Query(value = "            update tbl_file f set f.work_no = :work_no , f.use_yn = :use_yn,f.cd = :cd where f.file_no = :file_no ", nativeQuery = true)
    void updateFilesThumbnailByWorkIdAndIdAndCd(@Param("work_no") Long updateId,
                                                @Param("file_no") Long file_id,
                                                @Param("use_yn") String use_yn,
                                                @Param("cd") String cd);

    List<Files> findByUseYn(String useYn);
}
