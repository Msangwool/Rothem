package org.haram.rothem.repository.jpa;

import com.space.domain.rothem.entity.RoomAmenity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaRoomAmenityRepository extends JpaRepository<RoomAmenity, Long> {

    List<RoomAmenity> findAllByRoomSeq(Long roomSeq);

    void deleteByRoomSeq(Long roomSeq);

    void deleteAllByAmenitySeqIn(List<Long> amenitySeq);

}
