package org.haram.rothem.data.dto.admin.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor(staticName = "of")
public class RoomReservationTimeResponse {

    private RoomResponse roomResponse;

    // TODO 논의 후, 정책 관련 기능 사용할지 정의
//    private List<PolicyResponse> policyResponses;

    private List<AvailableCalendarResponse> calendarResponses;

}
