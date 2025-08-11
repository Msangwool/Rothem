package org.haram.rothem.repository.dao;

import org.haram.rothem.data.entity.RoomFile;
import org.haram.rothem.repository.jpa.JpaRoomFileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class RoomFileDao {

    private final JpaRoomFileRepository jpaRoomFileRepository;

    public List<RoomFile> findAll() {
        return jpaRoomFileRepository.findAll();
    }

    public List<RoomFile> findAllByRoomSeq(Long roomSeq) {
        return jpaRoomFileRepository.findAllByRoomSeq(roomSeq);
    }

    public Optional<RoomFile> findById(Long roomFileSeq) {
        return jpaRoomFileRepository.findById(roomFileSeq);
    }

    public Optional<RoomFile> save(RoomFile roomFile) {
        return Optional.of(jpaRoomFileRepository.save(roomFile));
    }

}
