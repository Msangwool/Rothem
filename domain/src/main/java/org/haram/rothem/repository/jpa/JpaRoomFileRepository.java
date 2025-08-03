package org.haram.rothem.repository.jpa;

import com.space.domain.rothem.entity.RoomFile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaRoomFileRepository extends JpaRepository<RoomFile, Long> {

    List<RoomFile> findAllByRoomSeq(Long roomSeq);

}
