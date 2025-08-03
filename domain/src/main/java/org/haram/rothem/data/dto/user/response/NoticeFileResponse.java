package org.haram.rothem.data.dto.user.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class NoticeFileResponse {

    private Long seq;

    private Long noticeSeq;

    private int sortNum;

    private String filePath;

    private final String createdBy = "";

    private final String createdAt = "";

    private final String modifiedBy = "";

    private final String modifiedAt = "";

}