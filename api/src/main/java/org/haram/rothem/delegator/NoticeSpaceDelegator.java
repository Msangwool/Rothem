package org.haram.rothem.delegator;

import org.haram.rothem.data.dto.notice.NoticeDetailSpaceEntity;
import org.haram.rothem.data.dto.notice.NoticeSpaceEntity;
import org.haram.rothem.data.type.NoticeType;

import java.util.List;

public interface NoticeSpaceDelegator {

    /**
     * 유효한 공지사항을 모두 불러옵니다.
     *
     * @return NoticeSpace 리스트를 반환합니다.
     */
    List<NoticeSpaceEntity> getNoticeAll();

    /**
     * 해당되는 유효한 공지사항을 불러옵니다.
     *
     * @param type 불러올 대상의 공지사항
     * @return NoticeSpace 리스트를 반환합니다.
     */
    List<NoticeSpaceEntity> getAllNoticeType(NoticeType type);


    NoticeDetailSpaceEntity getNoticeDetail(Long seq);
}
