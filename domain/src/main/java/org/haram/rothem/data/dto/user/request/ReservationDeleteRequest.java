package org.haram.rothem.data.dto.user.request;

import org.haram.rothem.exception.bodycode.RothemErrorCode;
import org.haram.rothem.exception.exception.HaramIllegalArgumentException;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReservationDeleteRequest {

    private Long reservationSeq;

    private String userId;

    public void validate() {
        if (reservationSeq == null) {
            throw new HaramIllegalArgumentException("reservationSeq 가 존재하지 않습니다.", RothemErrorCode.ILLEGAL_VALUE_RESERVATION_SEQ);
        }
    }

}
