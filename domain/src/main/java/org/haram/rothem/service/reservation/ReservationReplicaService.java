package org.haram.rothem.service.reservation;

import com.space.data.domain.rothem.admin.response.ReservationDetailResponse;
import com.space.data.type.rothem.ReservationStatus;
import com.space.domain.rothem.entity.*;
import com.space.domain.rothem.repository.dao.*;
import com.space.exception.bodycode.RothemErrorCode;
import com.space.exception.space.SpaceEntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReservationReplicaService {

    private final ReservationDao reservationDao;
    private final CalendarDao calendarDao;
    private final TimeStatusDao timeStatusDao;
    private final RoomDao roomDao;
    private final TimeDao timeDao;

    public Reservation findByUserIdAndReservationStatus(String userId, ReservationStatus reservationStatus) {
        return reservationDao.findByUserIdAndReservationStatus(userId, reservationStatus)
                .orElseThrow(() -> new SpaceEntityNotFoundException("예약 정보가 존재하지 않습니다.", RothemErrorCode.NOT_FOUND_RESERVATION));
    }

    public Reservation findById(Long reservationSeq) {
        return reservationDao.findById(reservationSeq)
                .orElseThrow(() -> new SpaceEntityNotFoundException("예약 정보가 존재하지 않습니다.", RothemErrorCode.NOT_FOUND_RESERVATION));
    }

    public List<Reservation> findAllByReservationStatus(ReservationStatus reservationStatus) {
        return reservationDao.findAllByReservationStatus(reservationStatus);
    }

    public List<Reservation> findAllByReservationSeqListAndReservationStatus(List<Long> reservationSeqList, ReservationStatus reservationStatus) {
        return reservationDao.findAllByReservationSeqListAndReservationStatus(reservationSeqList, reservationStatus);
    }

    public List<Reservation> findAll() {
        return reservationDao.findAll();
    }

    public Page<ReservationDetailResponse> findAll(Pageable pageable) {
        Page<Reservation> reservationPage = reservationDao.findAll(pageable);

        if (!reservationPage.hasContent()) {
            return createEmptyPage(pageable);
        }

        List<ReservationDetailResponse> reservationDetailResponses =
                reservationPage.getContent()
                        .stream()
                        .map(reservation -> getReservationDetailResponse(reservation.getReservationSeq(), reservation))
                        .toList();
        return new PageImpl<>(reservationDetailResponses, pageable, reservationPage.getTotalElements());
    }

    public Page<ReservationDetailResponse> findAllByUserInfo(Pageable pageable, String keyword) {
        Page<Reservation> reservationPage = reservationDao.findAllByUserInfo(pageable, keyword);

        if (!reservationPage.hasContent()) {
            return createEmptyPage(pageable);
        }

        List<ReservationDetailResponse> reservationDetailResponses =
                reservationPage.getContent()
                        .stream()
                        .map(reservation -> getReservationDetailResponse(reservation.getReservationSeq(), reservation))
                        .toList();
        return new PageImpl<>(reservationDetailResponses, pageable, reservationPage.getTotalElements());
    }

    public Page<ReservationDetailResponse> findAllByDate(Pageable pageable, LocalDate date) {
        CalendarUniqueKey calendarUniqueKey = CalendarUniqueKey.of(
                date.getDayOfWeek(),
                String.valueOf(date.getYear()),
                String.valueOf(date.getMonthValue()),
                String.valueOf(date.getDayOfMonth()));
        Calendar calendar = calendarDao.findByCalendarUniqueKey(calendarUniqueKey)
                .orElseThrow(() -> new SpaceEntityNotFoundException("Calendar 가 존재하지 않습니다.", RothemErrorCode.NOT_FOUND_CALENDAR));

        Page<Reservation> reservationPage = reservationDao.findAllByDate(pageable, calendar.getCalendarSeq());

        if (!reservationPage.hasContent()) {
            return createEmptyPage(pageable);
        }

        List<ReservationDetailResponse> reservationDetailResponses =
                reservationPage.getContent()
                        .stream()
                        .map(reservation -> getReservationDetailResponse(reservation.getReservationSeq(), reservation))
                        .toList();
        return new PageImpl<>(reservationDetailResponses, pageable, reservationPage.getTotalElements());
    }

    public boolean existsByUserIdAndReservationStatus(String userId, ReservationStatus reservationStatus) {
        return reservationDao.existsByUserIdAndReservationStatus(userId, reservationStatus);
    }

    public ReservationDetailResponse findByReservationSeq(Long reservationSeq) {
        Reservation reservation = reservationDao.findById(reservationSeq)
                .orElseThrow(() -> new SpaceEntityNotFoundException("예약 정보가 존재하지 않습니다.", RothemErrorCode.NOT_FOUND_RESERVATION));

        return getReservationDetailResponse(reservationSeq, reservation);
    }

    public ReservationDetailResponse findByReservationCode(String reservationCode) {
        Reservation reservation = reservationDao.findByReservationCode(reservationCode)
                .orElseThrow(() -> new SpaceEntityNotFoundException("예약 정보가 존재하지 않습니다.", RothemErrorCode.NOT_FOUND_RESERVATION));

        return getReservationDetailResponse(reservation.getReservationSeq(), reservation);
    }

    private ReservationDetailResponse getReservationDetailResponse(Long reservationSeq, Reservation reservation) {
        List<TimeStatus> timeStatuses = timeStatusDao.findAllByReservationSeq(reservationSeq);

        if (timeStatuses.isEmpty()) {
            throw new SpaceEntityNotFoundException("예약에 대한 시간 점보가 존재하지 않습니다.", RothemErrorCode.NOT_FOUND_TIME);
        }

        Long calendarSeq = timeStatuses.get(0).getCalendarSeq();
        Long roomSeq = timeStatuses.get(0).getRoomSeq();

        List<Long> timeSeqList = timeStatuses.stream().map(TimeStatus::getTimeSeq).toList();

        Room room = roomDao.findById(roomSeq)
                .orElseThrow(() -> new SpaceEntityNotFoundException("스터디룸 정보가 존재하지 않습니다.", RothemErrorCode.NOT_FOUND_ROOM));

        Calendar calendar = calendarDao.findById(calendarSeq)
                .orElseThrow(() -> new SpaceEntityNotFoundException("날짜 정보가 존재하지 않습니다.", RothemErrorCode.NOT_FOUND_CALENDAR));

        String calendarInfo = calendar.getYear() + "." + calendar.getMonth() + "." + calendar.getDate();

        List<Time> timeList = timeDao.findAllByTimeSeqList(timeSeqList);
        if (timeList.isEmpty()) {
            throw new SpaceEntityNotFoundException("시간 정보가 존재하지 않습니다.", RothemErrorCode.NOT_FOUND_TIME);
        }

        StringBuilder timeInfo = new StringBuilder();

        if (timeList.get(0).getMeridiem().equals("pm")) {
            timeInfo.append(Integer.parseInt(timeList.get(0).getHour()) + 12);
        } else {
            timeInfo.append(timeList.get(0).getHour());
        }
        timeInfo.append(":").append(timeList.get(0).getMinute());
        timeInfo.append(" ~ ");

        int timeListLength = timeList.size();
        if (timeList.get(timeListLength - 1).getMinute().equals("30")) {
            timeInfo.append((Integer.parseInt(timeList.get(timeListLength - 1).getHour()) +
                    (timeList.get(timeListLength - 1).getMeridiem().equals("pm") ? 13 : 1)));
        } else {
            timeInfo.append((Integer.parseInt(timeList.get(timeListLength - 1).getHour()) +
                    (timeList.get(timeListLength - 1).getMeridiem().equals("pm") ? 12 : 0)));
        }

        timeInfo.append(":").append(timeList.get(timeListLength - 1).getMinute().equals("30") ? "00" : "30");

        return ReservationDetailResponse.of(reservation.getReservationSeq(),
                reservation.getUserId(),
                reservation.getUserName(),
                reservation.getPhoneNum(),
                reservation.getReservationCode(),
                reservation.getReservationStatus().getTitle(),
                room.getRoomName(),
                calendarInfo,
                timeInfo.toString());
    }

    private <T> PageImpl<T> createEmptyPage(Pageable pageable) {
        return new PageImpl<>(new ArrayList<>(), pageable, 0);
    }

}
