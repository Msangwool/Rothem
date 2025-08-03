package org.haram.rothem.data.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.DayOfWeek;

@Getter
@Setter
@AllArgsConstructor(staticName = "of")
public class CalendarUniqueKey {

    private DayOfWeek day;

    private String year;

    private String month;

    private String date;

}
