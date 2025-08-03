package org.haram.rothem.data.dto.user.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReservationResponse {

    private Long reservationSeq;

    private String userId;

    private String userName;

    private String phoneNum;

    private String reservationCode;

    private String reservationStatus;

}
