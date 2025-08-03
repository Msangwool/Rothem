package org.haram.rothem.repository.dao;

import com.space.domain.rothem.entity.RoomAmenity;
import com.space.domain.rothem.repository.jpa.JpaRoomAmenityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class RoomAmenityDao {

    private final JpaRoomAmenityRepository jpaRoomAmenityRepository;

    public List<RoomAmenity> findAll() {
        return jpaRoomAmenityRepository.findAll();
    }

    public List<RoomAmenity> findAllByRoomSeq(Long roomSeq) {
        return jpaRoomAmenityRepository.findAllByRoomSeq(roomSeq);
    }

    public Optional<RoomAmenity> findById(Long roomAmenity) {
        return jpaRoomAmenityRepository.findById(roomAmenity);
    }

    public Optional<RoomAmenity> save(RoomAmenity roomAmenity) {
        return Optional.of(jpaRoomAmenityRepository.save(roomAmenity));
    }

    public void saveAll(List<RoomAmenity> roomAmenities) {
        jpaRoomAmenityRepository.saveAll(roomAmenities);
    }

    public void delete(List<Long> roomSeqs) {
        for (Long roomSeq : roomSeqs) {
            jpaRoomAmenityRepository.deleteByRoomSeq(roomSeq);
        }
    }

    public void deleteAllByAmenitySeqIn(List<Long> amenitySeqs) {
        jpaRoomAmenityRepository.deleteAllByAmenitySeqIn(amenitySeqs);
    }

    public void deleteAll(List<RoomAmenity> roomAmenities) {
        jpaRoomAmenityRepository.deleteAll(roomAmenities);
    }

}
