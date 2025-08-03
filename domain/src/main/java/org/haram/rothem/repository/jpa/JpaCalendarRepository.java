package org.haram.rothem.repository.jpa;

import com.space.data.type.rothem.WeekStatus;
import com.space.domain.rothem.entity.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JpaCalendarRepository extends JpaRepository<Calendar, Long> {

    List<Calendar> findAllByWeekStatus(WeekStatus weekStatus);

    Optional<Calendar> findByDayAndYearAndMonthAndDate(String day, String year, String month, String date);

    boolean existsByDayAndYearAndMonthAndDate(String day, String year, String month, String date);

}
