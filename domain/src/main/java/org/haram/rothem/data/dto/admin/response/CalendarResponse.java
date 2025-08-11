package org.haram.rothem.data.dto.admin.response;

import org.haram.rothem.data.type.WeekStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CalendarResponse {

    private Long calendarSeq;

    private String calendarYmd;

    private String day;

    private String year;

    private String month;

    private String date;

    private Boolean isAvailable;

    private WeekStatus weekStatus;

}
