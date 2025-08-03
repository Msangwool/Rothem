package org.haram.rothem.data.dto.admin.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RoomDeleteRequest {

    private List<Long> roomSeqs;

}
