package org.haram.rothem.service.notice;

import org.haram.rothem.data.entity.RothemNotice;
import org.haram.rothem.repository.dao.NoticeDao;
import org.haram.rothem.exception.bodycode.RothemErrorCode;
import org.haram.rothem.exception.exception.HaramEntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class NoticeReplicaService {

    private final NoticeDao noticeDao;

    public List<RothemNotice> findAll() {
        return noticeDao.findAll();
    }

    public Page<RothemNotice> findAll(Pageable pageable) {
        return noticeDao.findAll(pageable);
    }

    public List<RothemNotice> findAllByOrderByCreatedAtDesc() {
        return noticeDao.findAllByOrderByCreatedAtDesc();
    }

    public RothemNotice findById(Long noticeSeq) {
        return noticeDao.findById(noticeSeq)
                .orElseThrow(() -> new HaramEntityNotFoundException("Notice 를 찾을 수 없습니다.", RothemErrorCode.NOT_FOUND_NOTICE));
    }

}
