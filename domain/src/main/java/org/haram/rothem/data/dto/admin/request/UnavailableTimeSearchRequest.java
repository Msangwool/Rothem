package org.haram.rothem.data.dto.admin.request;

import com.space.exception.space.SpaceIllegalArgumentException;
import lombok.Getter;
import lombok.Setter;

import static com.space.exception.bodycode.RothemErrorCode.ILLEGAL_VALUE_CALENDAR_SEQ;
import static com.space.exception.bodycode.RothemErrorCode.ILLEGAL_VALUE_ROOM_SEQ;

@Getter
@Setter
public class UnavailableTimeSearchRequest {

    private Long calendarSeq;

    private Long roomSeq;

    public void validate() {
        if (calendarSeq == null) {
            throw new SpaceIllegalArgumentException("날짜 정보가 존재하지 않습니다.", ILLEGAL_VALUE_CALENDAR_SEQ);
        }

        if (roomSeq == null) {
            throw new SpaceIllegalArgumentException("스터디룸 정보가 존재하지 않습니다.", ILLEGAL_VALUE_ROOM_SEQ);
        }
    }

}
