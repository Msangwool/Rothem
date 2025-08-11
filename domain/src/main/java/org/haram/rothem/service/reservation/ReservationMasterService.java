package org.haram.rothem.service.reservation;

import org.haram.rothem.exception.bodycode.RothemErrorCode;
import org.haram.rothem.exception.exception.HaramEntityExistException;
import org.haram.rothem.exception.exception.HaramEntityNotFoundException;
import org.haram.rothem.exception.exception.HaramIllegalArgumentException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.haram.rothem.data.dto.user.request.ReservationDeleteRequest;
import org.haram.rothem.data.entity.Reservation;
import org.haram.rothem.data.entity.ReservationPolicy;
import org.haram.rothem.data.entity.TimeStatus;
import org.haram.rothem.data.entity.TimeStatusUniqueKey;
import org.haram.rothem.data.type.ReservationStatus;
import org.haram.rothem.repository.dao.ReservationDao;
import org.haram.rothem.repository.dao.ReservationPolicyDao;
import org.haram.rothem.repository.dao.TimeStatusDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ReservationMasterService {

    private final ReservationDao reservationDao;
    private final TimeStatusDao timeStatusDao;
    private final ReservationPolicyDao reservationPolicyDao;

    public Reservation save(Reservation reservation,
                            List<TimeStatus> timeStatuses,
                            List<ReservationPolicy> reservationPolicies) {
        // reservation
        Reservation savedReservation = getSavedReservation(reservation, timeStatuses);
        // reservation_policy
        for (ReservationPolicy reservationPolicy : reservationPolicies) {
            reservationPolicy.setReservationSeq(savedReservation.getReservationSeq());
            reservationPolicyDao.save(reservationPolicy)
                    .orElseThrow(() -> new HaramEntityExistException("이미 ReservationPolicy 가 존재합니다.", RothemErrorCode.ALREADY_EXIST_RESERVATION_POLICY));
        }

        return savedReservation;
    }

    public Reservation save(Reservation reservation,
                            List<TimeStatus> timeStatuses) {
        // reservation
        return getSavedReservation(reservation, timeStatuses);
    }

    private Reservation getSavedReservation(Reservation reservation, List<TimeStatus> timeStatuses) {
        Reservation savedReservation = reservationDao.save(reservation)
                .orElseThrow(() -> new HaramEntityExistException("이미 Reservation 이 존재합니다.", RothemErrorCode.ALREADY_EXIST_RESERVATION));
        // time_status
        for (TimeStatus timeStatus : timeStatuses) {
            if (timeStatusDao.existedByTimeStatusUniqueKey(
                    TimeStatusUniqueKey.of(
                            timeStatus.getRoomSeq(),
                            timeStatus.getCalendarSeq(),
                            timeStatus.getTimeSeq(),
                            timeStatus.getIsAvailable()))) {
                throw new HaramEntityExistException("이미 TimeStatus 가 존재합니다(이미 예약되어 있는 시간이 존재합니다.).", RothemErrorCode.ALREADY_EXIST_TIME_STATUS);
            }
            timeStatus.setReservationSeq(savedReservation.getReservationSeq());
            timeStatusDao.save(timeStatus)
                    .orElseThrow(() -> new HaramEntityNotFoundException("이미 TimeStatus 가 존재합니다.", RothemErrorCode.ALREADY_EXIST_TIME_STATUS));
        }
        return savedReservation;
    }

    public List<TimeStatus> saveAllUnavailableTime(List<TimeStatus> timeStatuses) {
        return timeStatusDao.saveAll(timeStatuses);
    }

    public void cancelReservation(ReservationDeleteRequest reservationDeleteRequest) {
        // reservation.reservation_status : RESERVED -> CANCEL_USER
        Reservation reservation = reservationDao.findById(reservationDeleteRequest.getReservationSeq())
                .orElseThrow(() -> new HaramEntityNotFoundException("Reservation 이 존재하지 않습니다.", RothemErrorCode.NOT_FOUND_RESERVATION));
        if (!reservation.getUserId().equals(reservationDeleteRequest.getUserId())) {
            throw new HaramIllegalArgumentException("올바르지 않은 요청입니다.", RothemErrorCode.ILLEGAL_USER);
        }
        reservation.setReservationStatus(ReservationStatus.CANCEL_USER);

        // TimeStatus 상태 처리
        List<TimeStatus> timeStatuses = timeStatusDao.findAllByReservationSeq(reservation.getReservationSeq());
        for (TimeStatus timeStatus : timeStatuses) {
            timeStatus.setIsAvailable(false);
        }

    }

    public void expireReservation(List<Long> expiredReservationSeqList) {
        List<Reservation> reservations = reservationDao.findAllByReservationSeqList(expiredReservationSeqList);

        for (Reservation reservation : reservations) {
            reservation.setReservationStatus(ReservationStatus.EXPIRED_RESERVATION);
        }
    }

    public void modifyStatus(Long reservationSeq, ReservationStatus reservationStatus) {
        Reservation currentReservation = reservationDao.findById(reservationSeq)
                .orElseThrow(() -> new HaramEntityNotFoundException("Reservation 이 존재하지 않습니다.", RothemErrorCode.NOT_FOUND_RESERVATION));
        currentReservation.setReservationStatus(reservationStatus);

        if (reservationStatus.equals(ReservationStatus.CANCEL_ADMIN)) {
            List<TimeStatus> timeStatuses = timeStatusDao.findAllByReservationSeq(reservationSeq);
            for (TimeStatus timeStatus : timeStatuses) {
                timeStatus.setIsAvailable(false);
            }
        }
    }

    public void modifyStatusByList(List<Long> reservationSeqList, ReservationStatus reservationStatus) {
        List<Reservation> savedReservationSeqList = reservationDao.findAllByReservationSeqList(reservationSeqList);
        for (Reservation reservation : savedReservationSeqList) {
            reservation.setReservationStatus(reservationStatus);
        }
    }

}