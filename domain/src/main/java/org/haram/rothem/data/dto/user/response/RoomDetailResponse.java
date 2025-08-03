package org.haram.rothem.data.dto.user.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor(staticName = "of")
public class RoomDetailResponse {

    private RoomResponse roomResponse;
    private List<RoomFileResponse> roomFileResponses;
    private List<AmenityResponse> amenityResponses;

}
