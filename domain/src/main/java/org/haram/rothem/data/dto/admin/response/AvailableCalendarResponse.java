package org.haram.rothem.data.dto.admin.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AvailableCalendarResponse {

    private Long calendarSeq;

    private String day;

    private String year;

    private String month;

    private String date;

    private Boolean isAvailable;

    List<AvailableTimeResponse> times;

}
