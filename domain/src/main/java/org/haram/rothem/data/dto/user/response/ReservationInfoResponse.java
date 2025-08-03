package org.haram.rothem.data.dto.user.response;

import com.space.data.type.rothem.ReservationStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ReservationInfoResponse {

    private Long reservationSeq;

    private String userId;

    private String userName;

    private String phoneNum;

    private String reservationCode;

    private ReservationStatus reservationStatus;

    private RoomResponse roomResponse;

    private CalendarResponse calendarResponse;

    private List<TimeResponse> timeResponses;

}
