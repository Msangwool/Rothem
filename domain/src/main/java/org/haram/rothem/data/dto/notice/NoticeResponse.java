package org.haram.rothem.data.dto.notice;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class NoticeResponse {

    private Long noticeSeq;
    private String title;
    private String content;
    private String thumbnailPath;
    private final String createdBy = "";
    private final String createdAt = "";
    private final String modifiedBy = "";
    private final String modifiedAt = "";

    public static NoticeResponse toNoticeResponse(NoticeDetailSpaceEntity entity) {
        return NoticeResponse.builder()
                .noticeSeq(1L)
                .title(entity.getTitle())
                .content(entity.getContent())
                .thumbnailPath(entity.getThumbnailPath())
                .build();
    }


    public static NoticeResponse toNoticeResponse(NoticeSpaceEntity entity) {
        return NoticeResponse.builder()
                .noticeSeq(1L)
                .title(entity.getTitle())
                .content("...")
                .thumbnailPath(entity.getThumbnailPath())
                .build();
    }



}
