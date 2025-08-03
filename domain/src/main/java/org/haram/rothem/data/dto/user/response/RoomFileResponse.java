package org.haram.rothem.data.dto.user.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoomFileResponse {

    private Long seq;

    private Long roomSeq;

    private int sortNum;

    private String filePath;

    private final String createdBy = "";

    private final String createdAt = "";

    private final String modifiedBy = "";

    private final String modifiedAt = "";

}
