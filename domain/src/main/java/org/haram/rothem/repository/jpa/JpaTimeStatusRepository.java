package org.haram.rothem.repository.jpa;

import com.space.domain.rothem.entity.TimeStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaTimeStatusRepository extends JpaRepository<TimeStatus, Long> {

    List<TimeStatus> findAllByReservationSeq(Long reservationSeq);

    List<TimeStatus> findAllByCalendarSeq(Long calendarSeq);

    boolean existsByRoomSeqAndCalendarSeqAndTimeSeqAndIsAvailable(Long roomSeq, Long calendarSeq,
                                                                  Long timeSeq, boolean isAvailable);

}
