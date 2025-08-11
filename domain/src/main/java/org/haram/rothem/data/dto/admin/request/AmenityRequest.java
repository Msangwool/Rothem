package org.haram.rothem.data.dto.admin.request;

import org.haram.rothem.exception.bodycode.RothemErrorCode;
import org.haram.rothem.exception.exception.HaramIllegalArgumentException;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;

@Getter
@Setter
public class AmenityRequest {

    private String title;

    private String filePath;

    public void validate() {
        if (!StringUtils.hasText(title)) {
            throw new HaramIllegalArgumentException("title is empty", RothemErrorCode.ILLEGAL_VALUE_TITLE);
        }

        if (!StringUtils.hasText(filePath)) {
            throw new HaramIllegalArgumentException("filePath is empty", RothemErrorCode.ILLEGAL_VALUE_FILE_PATH);
        }
    }

}