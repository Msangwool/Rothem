package org.haram.rothem.repository.jpa;

import com.space.domain.rothem.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaRoomRepository extends JpaRepository<Room, Long> {

    Optional<Room> findByRoomSeqAndStatusTrue(Long roomSeq);

}
