package org.haram.rothem.data.dto.admin.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor(staticName = "of")
public class ReservationDetailResponse {

    private Long reservationSeq;

    private String userId;

    private String userName;

    private String phoneNum;

    private String reservationCode;

    private String reservationStatus;

    private String roomName;

    private String calendar; // 2024.05.12

    private String time; // ex 00:00 ~ 03:00

}
