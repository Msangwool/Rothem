package org.haram.rothem.data.dto.notice;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.time.LocalDateTime;

@JsonSerialize(as = NoticeDetailSpaceEntity.class)
public interface NoticeDetailSpaceEntity {

    String getTitle();

    String getContent();

    String getThumbnailPath();

    String getCreatedBy();

    LocalDateTime getCreatedAt();

}

