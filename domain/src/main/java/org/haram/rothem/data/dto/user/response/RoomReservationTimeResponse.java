package org.haram.rothem.data.dto.user.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor(staticName = "of")
public class RoomReservationTimeResponse {

    private RoomResponse roomResponse;

    private List<PolicyResponse> policyResponses;

    private List<AvailableCalendarResponse> calendarResponses;

}
