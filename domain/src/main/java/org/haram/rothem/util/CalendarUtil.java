package org.haram.rothem.util;

import com.space.domain.rothem.entity.Calendar;
import com.space.domain.rothem.entity.CalendarUniqueKey;
import lombok.experimental.UtilityClass;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@UtilityClass
public class CalendarUtil {

    public static CalendarUniqueKey getCalendarUniqueKey(LocalDate localDate) {
        return CalendarUniqueKey.of(localDate.getDayOfWeek(),
                String.valueOf(localDate.getYear()),
                String.valueOf(localDate.getMonthValue()),
                String.valueOf(localDate.getDayOfMonth()));
    }

    public static boolean isNextWeek(LocalDate today, LocalDate date) {
        LocalDate startOfWeek = today.with(DayOfWeek.MONDAY);
        LocalDate endOfWeek = startOfWeek.plusDays(4);

        return !date.isBefore(startOfWeek) && !date.isAfter(endOfWeek);
    }

    public static boolean isPastWeek(Calendar calendar, LocalDate date) {
        return convertToLocalDate(calendar).isBefore(date);
    }

    public static LocalDate convertToLocalDate(Calendar calendar) {
        String year = calendar.getYear();
        String month = calendar.getMonth().length() == 1 ? "0" + calendar.getMonth() : calendar.getMonth();
        String date = calendar.getDate().length() == 1 ? "0" + calendar.getDate() : calendar.getDate();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(year + "-" + month + "-" + date, formatter);
    }

    public static String createCalendarYmd(String year, String month, String date) {
        month = month.length() == 1 ? "0" + month : month;
        date = date.length() == 1 ? "0" + date : date;
        return year + month + date;
    }

}
