package org.haram.rothem.service.calendar;

import com.space.data.domain.rothem.admin.request.CalendarAvailableRequest;
import com.space.data.type.rothem.WeekStatus;
import com.space.domain.rothem.entity.Calendar;
import com.space.domain.rothem.entity.CalendarUniqueKey;
import com.space.domain.rothem.repository.dao.CalendarDao;
import com.space.exception.bodycode.RothemErrorCode;
import com.space.exception.space.SpaceEntityExistException;
import com.space.exception.space.SpaceEntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class CalendarMasterService {


    private final CalendarDao calendarDao;

    public Calendar save(Calendar calendar) {
        return calendarDao.save(calendar)
                .orElseThrow(() -> new SpaceEntityExistException("이미 존재하는 Calendar 입니다.", RothemErrorCode.ALREADY_EXIST_CALENDAR));
    }

    public void saveAll(List<Calendar> calendars) {
        calendarDao.saveAll(calendars);
    }

    public void setWeekStatus(WeekStatus beforeStatus, WeekStatus afterStatus) {
        List<Calendar> calendars = calendarDao.findAllByWeekStatus(beforeStatus);
        for (Calendar calendar : calendars) {
            calendar.setWeekStatus(afterStatus);
        }
    }

    public void updateCalendarStatus(CalendarAvailableRequest calendarAvailableRequest) {
        Calendar currentCalendar = calendarDao.findById(calendarAvailableRequest.getCalendarSeq())
                .orElseThrow(() -> new SpaceEntityNotFoundException("Calendar 가 존재하지 않습니다.", RothemErrorCode.NOT_FOUND_CALENDAR));

        currentCalendar.setIsAvailable(calendarAvailableRequest.getIsAvailable());
    }

    public void modifyAllAvailable(List<CalendarAvailableRequest> calendarAvailableRequestList) {
        Map<Long, Boolean> availableInfo = calendarAvailableRequestList
                .stream()
                .collect(Collectors.toMap(CalendarAvailableRequest::getCalendarSeq, CalendarAvailableRequest::getIsAvailable,
                        (a, b) -> b));

        List<Calendar> currentCalendarList =
                calendarDao.findAllByCalendarSeqList(
                        calendarAvailableRequestList.stream().map(CalendarAvailableRequest::getCalendarSeq).toList());

        for (Calendar calendar : currentCalendarList) {
            calendar.setIsAvailable(availableInfo.get(calendar.getCalendarSeq()));
        }
    }

    public void updateCalendarWeekStatus(CalendarUniqueKey calendarUniqueKey, WeekStatus weekStatus) {
        Calendar currentCalendar = calendarDao.findByCalendarUniqueKey(calendarUniqueKey)
                .orElseThrow(() -> new SpaceEntityNotFoundException("Calendar 가 존재하지 않습니다.", RothemErrorCode.NOT_FOUND_CALENDAR));

        currentCalendar.setWeekStatus(weekStatus);
    }

    public void modify(Calendar calendar) {
        Calendar currentCalendar = calendarDao.findById(calendar.getCalendarSeq())
                .orElseThrow(() -> new SpaceEntityNotFoundException("Calendar 가 존재하지 않습니다.", RothemErrorCode.NOT_FOUND_CALENDAR));

        currentCalendar.setDay(calendar.getDay());
        currentCalendar.setYear(calendar.getYear());
        currentCalendar.setMonth(calendar.getMonth());
        currentCalendar.setDate(calendar.getDate());
        currentCalendar.setIsAvailable(calendar.getIsAvailable());
        currentCalendar.setWeekStatus(calendar.getWeekStatus());
    }
}
