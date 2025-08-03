package org.haram.rothem.data.dto.admin.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TimeResponse {

    private Long timeSeq;

    private String hour;

    private String minute;

    private String meridiem;

    private Boolean isAvailable;

}
