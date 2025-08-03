package org.haram.rothem.repository.dao;

import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.space.domain.rothem.entity.Room;
import com.space.domain.rothem.repository.jpa.JpaRoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

import static com.space.domain.rothem.entity.QRoom.room;

@Component
@RequiredArgsConstructor
@Slf4j
public class RoomDao {

    private final JPAQueryFactory jpaQueryFactory;

    private final JpaRoomRepository jpaRoomRepository;

    public List<Room> findAll() {
        return jpaQueryFactory.selectFrom(room)
                .where(room.status.eq(true))
                .orderBy(room.sortNum.asc())
                .fetch();
    }

    public Page<Room> findAll(Pageable pageable) {
        QueryResults<Room> queryResults = jpaQueryFactory.selectFrom(room)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(room.sortNum.asc())
                .fetchResults();
        return new PageImpl<>(queryResults.getResults(), pageable, queryResults.getTotal());
    }

    public List<Room> findAllById(List<Long> roomSeqs) {
        return jpaQueryFactory.selectFrom(room)
                .where(room.roomSeq.in(roomSeqs)
                        .and(room.status.eq(true)))
                .orderBy(room.sortNum.asc())
                .fetch();
    }

    public Optional<Room> findById(Long roomSeq) {
        return jpaRoomRepository.findById(roomSeq);
    }

    public Optional<Room> findByRoomSeqAndStatusTrue(Long roomSeq) {
        return jpaRoomRepository.findByRoomSeqAndStatusTrue(roomSeq);
    }

    public Optional<Room> findFirstOrderBySortNum() {
        return jpaQueryFactory.selectFrom(room)
                .orderBy(room.sortNum.desc())
                .stream()
                .findFirst();
    }

    public Optional<Room> save(Room room) {
        return Optional.of(jpaRoomRepository.save(room));
    }

    public void delete(List<Long> roomSeqs) {
        jpaRoomRepository.deleteAllById(roomSeqs);
    }

}
