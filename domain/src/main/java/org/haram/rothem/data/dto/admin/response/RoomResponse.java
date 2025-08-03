package org.haram.rothem.data.dto.admin.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoomResponse {

    private Long roomSeq;
    private String thumbnailPath;
    private String roomName;
    private String roomExplanation;
    private String location;
    private int peopleCount;
    private int sortNum;

}
