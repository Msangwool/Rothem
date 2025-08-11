package org.haram.rothem.data.dto.admin.request;

import org.haram.rothem.data.type.ReservationStatus;
import org.haram.rothem.exception.bodycode.RothemErrorCode;
import org.haram.rothem.exception.exception.HaramIllegalArgumentException;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReservationStatusRequest {

    private ReservationStatus status;

    public void validate() {
        if (status == null) {
            throw new HaramIllegalArgumentException("status is empty", RothemErrorCode.ILLEGAL_VALUE_RESERVATION_STATUS);
        }
    }

}
