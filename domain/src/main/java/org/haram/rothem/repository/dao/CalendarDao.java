package org.haram.rothem.repository.dao;

import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.haram.rothem.data.type.WeekStatus;
import org.haram.rothem.data.entity.Calendar;
import org.haram.rothem.data.entity.CalendarUniqueKey;
import org.haram.rothem.repository.jpa.JpaCalendarRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

import static org.haram.rothem.data.entity.QCalendar.calendar;

@Component
@RequiredArgsConstructor
@Slf4j
public class CalendarDao {


    private final JPAQueryFactory jpaQueryFactory;

    private final JpaCalendarRepository jpaCalendarRepository;

    public List<Calendar> findAll() {
        return jpaCalendarRepository.findAll();
    }

    public Page<Calendar> findAll(Pageable pageable) {
        QueryResults<Calendar> queryResults = jpaQueryFactory.selectFrom(calendar)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(calendar.calendarSeq.desc())
                .fetchResults();
        return new PageImpl<>(queryResults.getResults(), pageable, queryResults.getTotal());
    }

    public List<Calendar> findAllByWeekStatus(WeekStatus weekStatus) {
        return jpaCalendarRepository.findAllByWeekStatus(weekStatus);
    }

    public List<Calendar> findAllByWeekStatusList(List<WeekStatus> weekStatuses) {
        return jpaQueryFactory
                .selectFrom(calendar)
                .where(calendar.weekStatus.in(weekStatuses))
                .orderBy(calendar.calendarYmd.asc())
                .fetch();
    }

    public Page<Calendar> findAllByWeekStatusList(List<WeekStatus> weekStatuses, Pageable pageable) {
        QueryResults<Calendar> queryResults = jpaQueryFactory.selectFrom(calendar)
                .where(calendar.weekStatus.in(weekStatuses))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(calendar.calendarSeq.desc())
                .fetchResults();
        return new PageImpl<>(queryResults.getResults(), pageable, queryResults.getTotal());

    }

    public List<Calendar> findAllByCalendarSeqList(List<Long> calendarSeqList) {
        return jpaQueryFactory.selectFrom(calendar)
                .where(calendar.calendarSeq.in(calendarSeqList))
                .fetch();
    }

    public Optional<Calendar> findById(Long calendarSeq) {
        return jpaCalendarRepository.findById(calendarSeq);
    }

    public Optional<Calendar> findByCalendarUniqueKey(CalendarUniqueKey calendarUniqueKey) {
        return jpaCalendarRepository.findByDayAndYearAndMonthAndDate(calendarUniqueKey.getDay().toString(),
                calendarUniqueKey.getYear(), calendarUniqueKey.getMonth(), calendarUniqueKey.getDate());
    }

    public Optional<Calendar> save(Calendar calendar) {
        return Optional.of(jpaCalendarRepository.save(calendar));
    }

    public void saveAll(List<Calendar> calendars) {
        jpaCalendarRepository.saveAll(calendars);
    }

    public boolean existsByCalendarUniqueKey(CalendarUniqueKey calendarUniqueKey) {
        return jpaCalendarRepository.existsByDayAndYearAndMonthAndDate(calendarUniqueKey.getDay().toString(),
                calendarUniqueKey.getYear(), calendarUniqueKey.getMonth(), calendarUniqueKey.getDate());
    }
}
