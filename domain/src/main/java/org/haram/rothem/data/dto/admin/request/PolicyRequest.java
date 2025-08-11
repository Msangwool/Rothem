package org.haram.rothem.data.dto.admin.request;

import org.haram.rothem.exception.bodycode.RothemErrorCode;
import org.haram.rothem.exception.exception.HaramIllegalArgumentException;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;

@Getter
@Setter
public class PolicyRequest {

    private String title;

    private String content;

    private Boolean isRequired;

    public void validate() {
        if (!StringUtils.hasText(title)) {
            throw new HaramIllegalArgumentException("title is empty", RothemErrorCode.ILLEGAL_VALUE_TITLE);
        }

        if (!StringUtils.hasText(content)) {
            throw new HaramIllegalArgumentException("content is empty", RothemErrorCode.ILLEGAL_VALUE_TITLE);
        }
    }

}