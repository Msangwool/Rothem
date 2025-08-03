package org.haram.rothem.data.dto.admin.request;

import com.space.exception.bodycode.RothemErrorCode;
import com.space.exception.space.SpaceIllegalArgumentException;
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
            throw new SpaceIllegalArgumentException("title is empty", RothemErrorCode.ILLEGAL_VALUE_TITLE);
        }

        if (!StringUtils.hasText(filePath)) {
            throw new SpaceIllegalArgumentException("filePath is empty", RothemErrorCode.ILLEGAL_VALUE_FILE_PATH);
        }
    }

}