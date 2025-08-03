package org.haram.rothem.service.room;

import com.space.domain.rothem.entity.Room;
import com.space.domain.rothem.entity.RoomAmenity;
import com.space.domain.rothem.repository.dao.RoomAmenityDao;
import com.space.domain.rothem.repository.dao.RoomDao;
import com.space.exception.bodycode.RothemErrorCode;
import com.space.exception.space.SpaceEntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RoomReplicaService {

    private final RoomDao roomDao;
    private final RoomAmenityDao roomAmenityDao;

    public List<Room> findAll() {
        return roomDao.findAll();
    }

    public Page<Room> findAll(Pageable pageable) {
        return roomDao.findAll(pageable);
    }

    public Room findById(Long roomSeq) {
        return roomDao.findById(roomSeq)
                .orElseThrow(() -> new SpaceEntityNotFoundException("Room 이 존재하지 않습니다.", RothemErrorCode.NOT_FOUND_ROOM));
    }

    public Room findByIdAndStatusTrue(Long roomSeq) {
        return roomDao.findByRoomSeqAndStatusTrue(roomSeq)
                .orElseThrow(() -> new SpaceEntityNotFoundException("Room 이 존재하지 않습니다.", RothemErrorCode.NOT_FOUND_ROOM));
    }

    public Room findMaxSortNum() {
        return roomDao.findFirstOrderBySortNum()
                .orElseThrow(() -> new SpaceEntityNotFoundException("Room 이 존재하지 않습니다.", RothemErrorCode.NOT_FOUND_ROOM));
    }

    public List<RoomAmenity> findAllByRoomSeq(Long roomSeq) {
        return roomAmenityDao.findAllByRoomSeq(roomSeq);
    }

}
