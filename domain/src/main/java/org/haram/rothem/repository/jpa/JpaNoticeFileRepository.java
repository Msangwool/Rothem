package org.haram.rothem.repository.jpa;

import org.haram.rothem.data.entity.NoticeFile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaNoticeFileRepository extends JpaRepository<NoticeFile, Long> {

    List<NoticeFile> findAllByNoticeSeq(Long noticeSeq);

}
