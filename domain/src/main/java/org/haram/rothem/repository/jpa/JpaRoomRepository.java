package org.haram.rothem.repository.jpa;

import org.haram.rothem.data.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaRoomRepository extends JpaRepository<Room, Long> {

    Optional<Room> findByRoomSeqAndStatusTrue(Long roomSeq);

}
