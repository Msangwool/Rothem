package org.haram.rothem.service.room;

import org.haram.rothem.data.entity.RoomFile;
import org.haram.rothem.repository.dao.RoomFileDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RoomFileReplicaService {

    private final RoomFileDao roomFileDao;

    public List<RoomFile> findAllByRoomSeq(Long roomSeq) {
        return roomFileDao.findAllByRoomSeq(roomSeq);
    }

}
