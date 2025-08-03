package org.haram.rothem.repository.dao;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.space.domain.rothem.entity.ReservationCalendarVO;
import com.space.domain.rothem.entity.TimeStatus;
import com.space.domain.rothem.entity.TimeStatusUniqueKey;
import com.space.domain.rothem.repository.jpa.JpaTimeStatusRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

import static com.space.domain.rothem.entity.QTimeStatus.timeStatus;

@Component
@RequiredArgsConstructor
@Slf4j
public class TimeStatusDao {

    private final JPAQueryFactory jpaQueryFactory;

    private final JpaTimeStatusRepository jpaTimeStatusRepository;

    public List<TimeStatus> findAll() {
        return jpaTimeStatusRepository.findAll();
    }

    public List<ReservationCalendarVO> findAllGroupByReservationSeq(List<Long> reservationSeqList) {
        return jpaQueryFactory
                .select(Projections.constructor(
                        ReservationCalendarVO.class,
                        timeStatus.reservationSeq,
                        timeStatus.calendarSeq
                ))
                .from(timeStatus)
                .where(timeStatus.reservationSeq.in(reservationSeqList))
                .groupBy(timeStatus.reservationSeq, timeStatus.calendarSeq)
                .fetch();
    }

    public List<TimeStatus> findAllById(List<Long> timeStatusSeqList) {
        return jpaTimeStatusRepository.findAllById(timeStatusSeqList);
    }

    public List<TimeStatus> findAllByReservationSeq(Long reservationSeq) {
        return jpaTimeStatusRepository.findAllByReservationSeq(reservationSeq);
    }

    public List<TimeStatus> findAllByReservationSeqList(List<Long> reservationSeqList) {
        return jpaQueryFactory
                .selectFrom(timeStatus)
                .where(timeStatus.reservationSeq.in(reservationSeqList))
                .fetch();
    }

    public List<TimeStatus> findAllByCalendarSeq(Long calendarSeq) {
        return jpaTimeStatusRepository.findAllByCalendarSeq(calendarSeq);
    }

    public List<TimeStatus> findAllByCalendarSeqOrderByTimeSeq(Long calendarSeq, List<Long> roomSeqs) {
        return jpaQueryFactory
                .selectFrom(timeStatus)
                .where(timeStatus.roomSeq.in(roomSeqs),
                        timeStatus.calendarSeq.eq(calendarSeq))
                .orderBy(timeStatus.roomSeq.asc(), timeStatus.timeSeq.asc())
                .fetch();
    }

    public List<TimeStatus> findAllByTimeStatusKey(List<TimeStatusUniqueKey> timeStatusUniqueKeys) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();

        timeStatusUniqueKeys.forEach(timeStatusUniqueKey -> {
            booleanBuilder.or(timeStatus.roomSeq.eq(timeStatusUniqueKey.getRoomSeq())
                    .and(timeStatus.calendarSeq.eq(timeStatusUniqueKey.getCalendarSeq()))
                    .and(timeStatus.timeSeq.eq(timeStatusUniqueKey.getTimeSeq()))
                    .and(timeStatus.isAvailable.eq(timeStatusUniqueKey.getIsAvailable())));
        });

        return jpaQueryFactory.selectFrom(timeStatus)
                .where(booleanBuilder)
                .fetch();
    }

    public Optional<TimeStatus> findById(Long timeStatusSeq) {
        return jpaTimeStatusRepository.findById(timeStatusSeq);
    }


    public Optional<TimeStatus> findById(TimeStatusUniqueKey timeStatusUniqueKey) {
        return Optional.ofNullable(jpaQueryFactory.selectFrom(timeStatus)
                .where(timeStatus.roomSeq.eq(timeStatusUniqueKey.getRoomSeq())
                        .and(timeStatus.calendarSeq.eq(timeStatusUniqueKey.getCalendarSeq()))
                        .and(timeStatus.timeSeq.eq(timeStatusUniqueKey.getTimeSeq()))
                        .and(timeStatus.isAvailable.eq(timeStatusUniqueKey.getIsAvailable())))
                .fetchOne());
    }

    public Optional<TimeStatus> findByIdAndReservationSeqNot(TimeStatusUniqueKey timeStatusUniqueKey, Long reservationSeq) {
        return Optional.ofNullable(jpaQueryFactory.selectFrom(timeStatus)
                .where(timeStatus.roomSeq.eq(timeStatusUniqueKey.getRoomSeq())
                        .and(timeStatus.calendarSeq.eq(timeStatusUniqueKey.getCalendarSeq()))
                        .and(timeStatus.timeSeq.eq(timeStatusUniqueKey.getTimeSeq()))
                        .and(timeStatus.isAvailable.eq(timeStatusUniqueKey.getIsAvailable()))
                        .and(timeStatus.reservationSeq.ne(reservationSeq)))
                .fetchOne());
    }

    public boolean existedByTimeStatusUniqueKey(TimeStatusUniqueKey timeStatusUniqueKey) {
        return jpaTimeStatusRepository.existsByRoomSeqAndCalendarSeqAndTimeSeqAndIsAvailable(
                timeStatusUniqueKey.getRoomSeq(),
                timeStatusUniqueKey.getCalendarSeq(),
                timeStatusUniqueKey.getTimeSeq(),
                timeStatusUniqueKey.getIsAvailable());
    }

    public Optional<TimeStatus> save(TimeStatus timeStatus) {
        return Optional.of(jpaTimeStatusRepository.save(timeStatus));
    }

    public List<TimeStatus> saveAll(List<TimeStatus> timeStatuses) {
        return jpaTimeStatusRepository.saveAll(timeStatuses);
    }

    public void modifyUnavailableTimeStatus(List<TimeStatusUniqueKey> timeStatusUniqueKeys) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();

        timeStatusUniqueKeys.forEach(timeStatusUniqueKey -> {
            booleanBuilder.or(timeStatus.roomSeq.eq(timeStatusUniqueKey.getRoomSeq())
                    .and(timeStatus.calendarSeq.eq(timeStatusUniqueKey.getCalendarSeq()))
                    .and(timeStatus.timeSeq.eq(timeStatusUniqueKey.getTimeSeq()))
                    .and(timeStatus.isAvailable.eq(timeStatusUniqueKey.getIsAvailable()))
                    .and(timeStatus.reservationSeq.eq(-1L)));
        });

        jpaQueryFactory.update(timeStatus)
                .set(timeStatus.isAvailable, false)
                .where(booleanBuilder)
                .execute();
    }

    public Optional<Boolean> delete(TimeStatus timeStatus) {
        try {
            jpaTimeStatusRepository.delete(timeStatus);
            return Optional.of(true);
        } catch (Exception e) {
            log.warn("TimeStatusDao - delete() : Exception -> {}, Message -> {}", e.getClass(), e.getMessage());
            return Optional.empty();
        }
    }

}
