package org.haram.rothem.data.dto.admin.request;

import org.haram.rothem.data.dto.user.request.TimeRequest;
import org.haram.rothem.exception.bodycode.RothemErrorCode;
import org.haram.rothem.exception.exception.HaramIllegalArgumentException;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;

import java.util.List;

@Getter
@Setter
public class ReservationRequest {

    private String adminId;

    private String userName;

    private String phoneNum;

    private Long calendarSeq;

    private Long roomSeq;

    private List<TimeRequest> timeRequests;

    public void validate() {
        if (!StringUtils.hasText(userName)) {
            throw new HaramIllegalArgumentException("userName is empty", RothemErrorCode.ILLEGAL_VALUE_USERNAME);
        }

        if (!StringUtils.hasText(phoneNum)) {
            throw new HaramIllegalArgumentException("phoneNum is empty", RothemErrorCode.ILLEGAL_VALUE_PHONE_NUM);
        }

        if (calendarSeq == null) {
            throw new HaramIllegalArgumentException("calendarSeq is empty", RothemErrorCode.ILLEGAL_VALUE_CALENDAR_SEQ);
        }

        if (timeRequests == null || timeRequests.isEmpty()) {
            throw new HaramIllegalArgumentException("timeRequests is empty", RothemErrorCode.ILLEGAL_VALUE_TIME_REQUEST);
        }

    }


}