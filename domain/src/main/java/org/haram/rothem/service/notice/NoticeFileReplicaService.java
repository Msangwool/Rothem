package org.haram.rothem.service.notice;

import com.space.domain.rothem.entity.NoticeFile;
import com.space.domain.rothem.repository.dao.NoticeFileDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class NoticeFileReplicaService {

    private final NoticeFileDao noticeFileDao;

    public List<NoticeFile> findAll() {
        return noticeFileDao.findAll();
    }

    public List<NoticeFile> findAllByNoticeSeq(Long noticeSeq) {
        return noticeFileDao.findAllByNoticeSeq(noticeSeq);
    }

}
