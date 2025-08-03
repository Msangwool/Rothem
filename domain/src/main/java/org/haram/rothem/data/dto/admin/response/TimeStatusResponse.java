package org.haram.rothem.data.dto.admin.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TimeStatusResponse {

    private Long timeStatusSeq;

    private Long roomSeq;

    private Long calendarSeq;

    private Long timeSeq;

    private Long reservationSeq;

    private String requestType;

    private boolean isAvailable;

}
