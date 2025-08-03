package org.haram.rothem.data.dto.user.request;

import com.space.exception.bodycode.RothemErrorCode;
import com.space.exception.space.SpaceIllegalArgumentException;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;

import java.util.List;

@Getter
@Setter
public class ReservationRequest {

    private String userId;

    private String userName;

    private String phoneNum;

    private Long calendarSeq;

    private Long roomSeq;

    private List<ReservationPolicyRequest> reservationPolicyRequests;

    private List<TimeRequest> timeRequests;

    public void validate() {
        if (!StringUtils.hasText(userName)) {
            throw new SpaceIllegalArgumentException("userName is empty", RothemErrorCode.ILLEGAL_VALUE_USERNAME);
        }

        if (!StringUtils.hasText(phoneNum)) {
            throw new SpaceIllegalArgumentException("phoneNum is empty", RothemErrorCode.ILLEGAL_VALUE_PHONE_NUM);
        }

        if (calendarSeq == null) {
            throw new SpaceIllegalArgumentException("calendarSeq is empty", RothemErrorCode.ILLEGAL_VALUE_CALENDAR_SEQ);
        }

        if (timeRequests == null || timeRequests.isEmpty()) {
            throw new SpaceIllegalArgumentException("timeRequests is empty", RothemErrorCode.ILLEGAL_VALUE_TIME_REQUEST);
        }

    }


}