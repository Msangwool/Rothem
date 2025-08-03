package org.haram.rothem.data.dto.admin.request;

import com.space.data.type.rothem.ReservationStatus;
import com.space.exception.bodycode.RothemErrorCode;
import com.space.exception.space.SpaceIllegalArgumentException;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReservationStatusRequest {

    private ReservationStatus status;

    public void validate() {
        if (status == null) {
            throw new SpaceIllegalArgumentException("status is empty", RothemErrorCode.ILLEGAL_VALUE_RESERVATION_STATUS);
        }
    }

}
