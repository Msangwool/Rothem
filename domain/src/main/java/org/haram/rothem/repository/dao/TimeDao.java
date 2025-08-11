package org.haram.rothem.repository.dao;

import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.haram.rothem.data.entity.Time;
import org.haram.rothem.data.entity.TimeUniqueKey;
import org.haram.rothem.repository.jpa.JpaTimeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

import static org.haram.rothem.data.entity.QTime.time;

@Component
@RequiredArgsConstructor
@Slf4j
public class TimeDao {

    private final JPAQueryFactory jpaQueryFactory;

    private final JpaTimeRepository jpaTimeRepository;

    public List<Time> findAll() {
        return jpaTimeRepository.findAll();
    }

    public Page<Time> findAll(Pageable pageable) {
        QueryResults<Time> queryResults = jpaQueryFactory.selectFrom(time)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(time.timeSeq.asc())
                .fetchResults();
        return new PageImpl<>(queryResults.getResults(), pageable, queryResults.getTotal());
    }

    public List<Time> findAllByIsAvailable(boolean isAvailable) {
        return jpaTimeRepository.findAllByIsAvailable(isAvailable);
    }

    public List<Time> findAllByTimeSeqList(List<Long> timeSeqList) {
        return jpaQueryFactory.selectFrom(time)
                .where(time.timeSeq.in(timeSeqList))
                .orderBy(time.timeSeq.asc())
                .fetch();
    }

    public Optional<Time> findByTimeUniqueKey(TimeUniqueKey timeUniqueKey) {
        return jpaTimeRepository.findByHourAndMinuteAndMeridiem(timeUniqueKey.hour,
                timeUniqueKey.minute, timeUniqueKey.meridiem);
    }

    public Optional<Time> findById(Long timeSeq) {
        return jpaTimeRepository.findById(timeSeq);
    }

    public Optional<Time> save(Time time) {
        return Optional.of(jpaTimeRepository.save(time));
    }

}
