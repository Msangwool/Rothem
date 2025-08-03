package org.haram.rothem.repository.dao;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.space.data.type.rothem.ReservationStatus;
import com.space.data.type.rothem.ReservationType;
import com.space.domain.rothem.entity.Reservation;
import com.space.domain.rothem.repository.jpa.JpaReservationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

import static com.space.domain.rothem.entity.QCalendar.calendar;
import static com.space.domain.rothem.entity.QReservation.reservation;
import static com.space.domain.rothem.entity.QTimeStatus.timeStatus;

@Component
@RequiredArgsConstructor
@Slf4j
public class ReservationDao {

    private final JPAQueryFactory jpaQueryFactory;

    private final JpaReservationRepository jpaReservationRepository;

    public List<Reservation> findAll() {
        return jpaQueryFactory.selectFrom(reservation)
                .orderBy(reservation.reservationSeq.desc())
                .fetch();
    }

    public Page<Reservation> findAll(Pageable pageable) {
        QueryResults<Reservation> queryResults = jpaQueryFactory
                .select(reservation)
                .from(timeStatus)
                .join(calendar).on(timeStatus.calendarSeq.eq(calendar.calendarSeq))
                .join(reservation).on(timeStatus.reservationSeq.eq(reservation.reservationSeq))
                .where(
                        timeStatus.reservationType.eq(ReservationType.RESERVED)
                )
                .groupBy(timeStatus.reservationSeq)
                .orderBy(
                        calendar.calendarYmd.min().desc(),
                        timeStatus.timeSeq.min().desc(),
                        timeStatus.roomSeq.min().asc(),
                        reservation.createdAt.desc()
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        return new PageImpl<>(queryResults.getResults(), pageable, queryResults.getTotal());
    }

    public Page<Reservation> findAllByUserInfo(Pageable pageable, String keyword) {
        QueryResults<Reservation> queryResults = jpaQueryFactory
                .select(reservation)
                .from(timeStatus)
                .join(calendar).on(timeStatus.calendarSeq.eq(calendar.calendarSeq))
                .join(reservation).on(timeStatus.reservationSeq.eq(reservation.reservationSeq))
                .where(
                        timeStatus.reservationType.eq(ReservationType.RESERVED),
                        (reservation.userName.eq(keyword)
                                .or(reservation.phoneNum.eq(keyword)))
                )
                .groupBy(timeStatus.reservationSeq)
                .orderBy(
                        calendar.calendarYmd.min().desc(),
                        timeStatus.timeSeq.min().desc(),
                        timeStatus.roomSeq.min().asc()
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        return new PageImpl<>(queryResults.getResults(), pageable, queryResults.getTotal());
    }

    public Page<Reservation> findAllByDate(Pageable pageable, Long calendarSeq) {
        QueryResults<Reservation> queryResults = jpaQueryFactory
                .select(reservation)
                .from(timeStatus)
                .join(reservation).on(timeStatus.reservationSeq.eq(reservation.reservationSeq))
                .where(
                        timeStatus.reservationType.eq(ReservationType.RESERVED),
                        timeStatus.calendarSeq.eq(calendarSeq)
                )
                .groupBy(timeStatus.reservationSeq)
                .orderBy(
                        timeStatus.timeSeq.min().asc(),
                        timeStatus.roomSeq.min().asc()
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        return new PageImpl<>(queryResults.getResults(), pageable, queryResults.getTotal());
    }

    public List<Reservation> findAllByUserId(String userId) {
        return jpaReservationRepository.findAllByUserId(userId);
    }

    public List<Reservation> findAllByReservationSeqList(List<Long> reservationSeqList) {
        return jpaQueryFactory.selectFrom(reservation)
                .where(reservation.reservationSeq.in(reservationSeqList))
                .fetch();
    }

    public Page<Reservation> findAllByReservationSeqList(Pageable pageable, List<Long> reservationSeqList) {
        QueryResults<Reservation> queryResults = jpaQueryFactory.selectFrom(reservation)
                .where(reservation.reservationSeq.in(reservationSeqList))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(orderByFieldList(reservationSeqList))
                .fetchResults();
        return new PageImpl<>(queryResults.getResults(), pageable, queryResults.getTotal());
    }

    private OrderSpecifier<String> orderByFieldList(List<Long> seqList) {
        return Expressions
                .stringTemplate("FIELD({0}, {1})", reservation.reservationSeq, seqList)
                .asc();
    }

    public List<Reservation> findAllByReservationSeqListAndReservationStatus(List<Long> reservationSeqList, ReservationStatus reservationStatus) {
        return jpaQueryFactory.selectFrom(reservation)
                .where(reservation.reservationSeq.in(reservationSeqList)
                        .and(reservation.reservationStatus.eq(reservationStatus)))
                .fetch();
    }

    public List<Reservation> findAllByReservationStatus(ReservationStatus reservationStatus) {
        return jpaReservationRepository.findAllByReservationStatus(reservationStatus);
    }

    public Optional<Reservation> findByUserIdAndReservationStatus(String userId, ReservationStatus reservationStatus) {
        return jpaReservationRepository.findByUserIdAndReservationStatus(userId, reservationStatus);
    }

    public Optional<Reservation> findById(Long reservationSeq) {
        return jpaReservationRepository.findById(reservationSeq);
    }

    public Optional<Reservation> findByReservationCode(String reservationCode) {
        return jpaReservationRepository.findByReservationCode(reservationCode);
    }

    public boolean existsByUserIdAndReservationStatus(String userId, ReservationStatus reservationStatus) {
        return jpaReservationRepository.existsByUserIdAndReservationStatus(userId, reservationStatus);
    }

    public Optional<Reservation> save(Reservation reservation) {
        return Optional.of(jpaReservationRepository.save(reservation));
    }

    public Optional<Boolean> delete(Reservation reservation) {
        try {
            jpaReservationRepository.delete(reservation);
            return Optional.of(true);
        } catch (Exception e) {
            log.warn("ReservationDao - delete() : Exception -> {}, Message -> {}", e.getClass(), e.getMessage());
            return Optional.empty();
        }
    }

}
