package org.haram.rothem.data.dto.notice;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(as = NoticeSpaceEntity.class)
public interface NoticeSpaceEntity {

    Long getNoticeSeq();

    String getTitle();

    String getThumbnailPath();

}
