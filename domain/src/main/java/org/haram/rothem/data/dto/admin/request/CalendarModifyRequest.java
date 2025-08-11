package org.haram.rothem.data.dto.admin.request;

import org.haram.rothem.data.type.WeekStatus;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;

import java.time.DayOfWeek;

@Getter
@Setter
public class CalendarModifyRequest {

    private Long calendarSeq;

    private DayOfWeek day;

    private String year;

    private String month;

    private String date;

    private Boolean isAvailable;

    private WeekStatus weekStatus;

    public void validate() {
        if (calendarSeq == null) {
            throw new IllegalArgumentException("calendarSeq is empty");
        }
        if (!StringUtils.hasText(year)) {
            throw new IllegalArgumentException("year is empty");
        }
        if (!StringUtils.hasText(month)) {
            throw new IllegalArgumentException("month is empty");
        }
        if (!StringUtils.hasText(date)) {
            throw new IllegalArgumentException("date is empty");
        }
        if (isAvailable == null) {
            throw new IllegalArgumentException("isAvailable is empty");
        }
        if (weekStatus == null) {
            throw new IllegalArgumentException("weekStatus is empty");
        }
    }

}
