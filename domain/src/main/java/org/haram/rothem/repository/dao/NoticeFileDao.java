package org.haram.rothem.repository.dao;

import org.haram.rothem.data.entity.NoticeFile;
import org.haram.rothem.repository.jpa.JpaNoticeFileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class NoticeFileDao {

    private final JpaNoticeFileRepository jpaNoticeFileRepository;

    public List<NoticeFile> findAll() {
        return jpaNoticeFileRepository.findAll();
    }

    public List<NoticeFile> findAllByNoticeSeq(Long noticeSeq) {
        return jpaNoticeFileRepository.findAllByNoticeSeq(noticeSeq);
    }

    public Optional<NoticeFile> findById(Long noticeFileSeq) {
        return jpaNoticeFileRepository.findById(noticeFileSeq);
    }

    public Optional<NoticeFile> save(NoticeFile noticeFile) {
        try {
            return Optional.of(jpaNoticeFileRepository.save(noticeFile));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

}
