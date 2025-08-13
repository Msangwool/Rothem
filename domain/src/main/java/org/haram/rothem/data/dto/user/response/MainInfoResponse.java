package org.haram.rothem.data.dto.user.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor(staticName = "of")
public class MainInfoResponse {

    private List<RoomResponse> roomResponses;

    private int isReserved;

}
