package org.haram.rothem.service.time;

import com.space.data.domain.rothem.admin.response.UnavailableTimeResponse;
import com.space.data.domain.rothem.admin.response.UnavailableTimeStatus;
import com.space.domain.rothem.entity.ReservationCalendarVO;
import com.space.domain.rothem.entity.Time;
import com.space.domain.rothem.entity.TimeStatus;
import com.space.domain.rothem.entity.TimeStatusUniqueKey;
import com.space.domain.rothem.repository.dao.TimeDao;
import com.space.domain.rothem.repository.dao.TimeStatusDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TimeStatusReplicaService {

    private final TimeStatusDao timeStatusDao;
    private final TimeDao timeDao;

    public List<TimeStatus> findAllByReservationSeq(Long reservationSeq) {
        return timeStatusDao.findAllByReservationSeq(reservationSeq);
    }

    public List<ReservationCalendarVO> findAllGroupByReservationSeq(List<Long> reservationSeqList) {
        return timeStatusDao.findAllGroupByReservationSeq(reservationSeqList);
    }

    public List<TimeStatus> findAllByTimeStatusKey(List<TimeStatusUniqueKey> timeStatusUniqueKeys) {
        return timeStatusDao.findAllByTimeStatusKey(timeStatusUniqueKeys);
    }

    public Optional<TimeStatus> findById(TimeStatusUniqueKey timeStatusUniqueKey) {
        return timeStatusDao.findById(timeStatusUniqueKey);
    }

    public Optional<TimeStatus> findByIdAndReservationSeqNot(TimeStatusUniqueKey timeStatusUniqueKey, Long reservationSeq) {
        return timeStatusDao.findByIdAndReservationSeqNot(timeStatusUniqueKey, reservationSeq);
    }

    public boolean existedByTimeStatusUniqueKey(TimeStatusUniqueKey timeStatusUniqueKey) {
        return timeStatusDao.existedByTimeStatusUniqueKey(timeStatusUniqueKey);
    }

    public UnavailableTimeResponse findAllUnavailableTimes(Long calendarSeq, Long roomSeq) {
        List<Time> times = timeDao.findAllByIsAvailable(true);
        List<TimeStatusUniqueKey> timeStatusUniqueKeys = times
                .stream()
                .map(time -> TimeStatusUniqueKey.of(roomSeq, calendarSeq, time.getTimeSeq(), true))
                .toList();

        // 불가능한 시간 조회
        Set<Long> existedTimeSeqList = timeStatusDao.findAllByTimeStatusKey(timeStatusUniqueKeys)
                .stream()
                .map(TimeStatus::getTimeSeq)
                .collect(Collectors.toSet());

        List<UnavailableTimeStatus> unavailableTimeStatuses =
                times.stream()
                        .map(time -> {
                            String timeText = String.format("%02d:%02d",
                                    time.getMeridiem().equals("pm") ? Integer.parseInt(time.getHour()) + 12 : Integer.parseInt(time.getHour()),
                                    Integer.parseInt(time.getMinute()));

                            return UnavailableTimeStatus.of(
                                    time.getTimeSeq(),
                                    timeText,
                                    existedTimeSeqList.contains(time.getTimeSeq()));
                        })
                        .toList();
        return UnavailableTimeResponse.of(calendarSeq, roomSeq, unavailableTimeStatuses);
    }


}
