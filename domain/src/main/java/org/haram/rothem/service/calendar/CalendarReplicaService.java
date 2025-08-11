package org.haram.rothem.service.calendar;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.haram.rothem.data.entity.Calendar;
import org.haram.rothem.data.entity.CalendarUniqueKey;
import org.haram.rothem.data.type.WeekStatus;
import org.haram.rothem.exception.bodycode.RothemErrorCode;
import org.haram.rothem.exception.exception.HaramEntityNotFoundException;
import org.haram.rothem.repository.dao.CalendarDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CalendarReplicaService {

    private final CalendarDao calendarDao;

    public List<Calendar> findAll() {
        return calendarDao.findAll();
    }

    public Page<Calendar> findAll(Pageable pageable) {
        return calendarDao.findAll(pageable);
    }

    public List<Calendar> findAllByCalendarSeqList(List<Long> calendarSeqList) {
        return calendarDao.findAllByCalendarSeqList(calendarSeqList);
    }

    public List<Calendar> findAllByWeekStatus(WeekStatus weekStatus) {
        return calendarDao.findAllByWeekStatus(weekStatus);
    }

    public List<Calendar> findAllByWeekStatusList(List<WeekStatus> weekStatusList) {
        return calendarDao.findAllByWeekStatusList(weekStatusList);
    }

    public Page<Calendar> findAllByWeekStatusList(List<WeekStatus> weekStatusList, Pageable pageable) {
        return calendarDao.findAllByWeekStatusList(weekStatusList, pageable);
    }

    public Calendar findById(Long calendarSeq) {
        return calendarDao.findById(calendarSeq)
                .orElseThrow(() -> new HaramEntityNotFoundException("Calendar 가 존재하지 않습니다.", RothemErrorCode.NOT_FOUND_CALENDAR));
    }

    public Optional<Calendar> findByCalendarUniqueKey(CalendarUniqueKey calendarUniqueKey) {
        return calendarDao.findByCalendarUniqueKey(calendarUniqueKey);
    }

    public boolean existByCalendarUniqueKey(CalendarUniqueKey calendarUniqueKey) {
        return calendarDao.existsByCalendarUniqueKey(calendarUniqueKey);
    }
}
