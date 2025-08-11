package org.haram.rothem.data.dto.admin.request;

import org.haram.rothem.exception.bodycode.RothemErrorCode;
import org.haram.rothem.exception.exception.HaramIllegalArgumentException;
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
            throw new HaramIllegalArgumentException("thumbnailPath is empty", RothemErrorCode.ILLEGAL_VALUE_FILE_PATH);
        }

        if (!StringUtils.hasText(roomName)) {
            throw new HaramIllegalArgumentException("roomName is empty", RothemErrorCode.ILLEGAL_VALUE_ROOM_NAME);
        }
    }

}
