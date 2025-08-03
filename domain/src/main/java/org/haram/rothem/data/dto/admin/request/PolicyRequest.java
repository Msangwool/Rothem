package org.haram.rothem.data.dto.admin.request;

import com.space.exception.bodycode.RothemErrorCode;
import com.space.exception.space.SpaceIllegalArgumentException;
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
            throw new SpaceIllegalArgumentException("title is empty", RothemErrorCode.ILLEGAL_VALUE_TITLE);
        }

        if (!StringUtils.hasText(content)) {
            throw new SpaceIllegalArgumentException("content is empty", RothemErrorCode.ILLEGAL_VALUE_TITLE);
        }
    }

}