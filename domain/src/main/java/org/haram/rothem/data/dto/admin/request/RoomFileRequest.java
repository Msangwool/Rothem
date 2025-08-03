package org.haram.rothem.data.dto.admin.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;

@Getter
@Setter
public class RoomFileRequest {

    private Long roomSeq;

    private int sortNum;

    private String filePath;

    public void validate() {
        if (!StringUtils.hasText(filePath)) {
            throw new IllegalArgumentException("filePath is empty");
        }
    }

}
