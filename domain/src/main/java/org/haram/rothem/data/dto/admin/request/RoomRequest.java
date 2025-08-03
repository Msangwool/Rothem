package org.haram.rothem.data.dto.admin.request;

import com.space.exception.bodycode.RothemErrorCode;
import com.space.exception.space.SpaceIllegalArgumentException;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;

import java.util.List;

@Getter
@Setter
public class RoomRequest {

    private String thumbnailPath;

    private String roomName;

    private String roomExplanation;

    private String location;

    private Integer peopleCount;

    private Integer sortNum;

    private List<Long> amenitySeqs;

    public void validate() {
        if (!StringUtils.hasText(thumbnailPath)) {
            throw new SpaceIllegalArgumentException("thumbnailPath is empty", RothemErrorCode.ILLEGAL_VALUE_FILE_PATH);
        }

        if (!StringUtils.hasText(roomName)) {
            throw new SpaceIllegalArgumentException("roomName is empty", RothemErrorCode.ILLEGAL_VALUE_ROOM_NAME);
        }
    }

}
