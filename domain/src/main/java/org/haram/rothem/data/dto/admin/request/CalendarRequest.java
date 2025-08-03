package org.haram.rothem.data.dto.admin.request;

import com.space.data.type.rothem.WeekStatus;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;

import java.time.DayOfWeek;

@Getter
@Setter
public class CalendarRequest {

    private DayOfWeek day;

    private String year;

    private String month;

    private String date;

    private Boolean isAvailable;

    private WeekStatus weekStatus;

    public void validate() {
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