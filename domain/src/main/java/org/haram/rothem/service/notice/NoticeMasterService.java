package org.haram.rothem.service.notice;

import org.haram.rothem.data.entity.NoticeFile;
import org.haram.rothem.data.entity.RothemNotice;
import org.haram.rothem.repository.dao.NoticeDao;
import org.haram.rothem.repository.dao.NoticeFileDao;
import org.haram.rothem.exception.bodycode.RothemErrorCode;
import org.haram.rothem.exception.exception.HaramEntityExistException;
import org.haram.rothem.exception.exception.HaramEntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class NoticeMasterService {

    private final NoticeDao noticeDao;
    private final NoticeFileDao noticeFileDao;

    public RothemNotice save(RothemNotice rothemNotice) {
        return noticeDao.save(rothemNotice)
                .orElseThrow(() -> new HaramEntityExistException("이미 Notice 가 존재합니다.", RothemErrorCode.ALREADY_EXIST_NOTICE));
    }

    public RothemNotice save(RothemNotice rothemNotice, List<NoticeFile> noticeFiles) {
        RothemNotice savedRothemNotice = noticeDao.save(rothemNotice)
                .orElseThrow(() -> new HaramEntityExistException("이미 Notice 가 존재합니다.", RothemErrorCode.ALREADY_EXIST_NOTICE));

        for (NoticeFile noticeFile : noticeFiles) {
            noticeFile.setNoticeSeq(savedRothemNotice.getNoticeSeq());
            noticeFileDao.save(noticeFile);
        }

        return savedRothemNotice;
    }

    public RothemNotice modify(RothemNotice rothemNotice) {
        RothemNotice currentRothemNotice = noticeDao.findById(rothemNotice.getNoticeSeq())
                .orElseThrow(() -> new HaramEntityNotFoundException("Notice 가 존재하지 않습니다.", RothemErrorCode.NOT_FOUND_NOTICE));

        if (rothemNotice.getThumbnailPath() != null) {
            currentRothemNotice.setThumbnailPath(rothemNotice.getThumbnailPath());
        }
        currentRothemNotice.setTitle(rothemNotice.getTitle());
        currentRothemNotice.setContent(rothemNotice.getContent());

        return currentRothemNotice;
    }

    public boolean delete(List<Long> noticeSeqs) {
        try {
            noticeDao.delete(noticeSeqs);
            return true;
        } catch (Exception e) {
            log.warn("[NoticeMasterService] delete -> notice 리스트 삭제 실패, {}", noticeSeqs.toString());
            return false;
        }
    }

}
