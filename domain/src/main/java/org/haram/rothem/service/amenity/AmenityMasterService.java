package org.haram.rothem.service.amenity;

import org.haram.rothem.data.entity.Amenity;
import org.haram.rothem.repository.dao.AmenityDao;
import org.haram.rothem.repository.dao.RoomAmenityDao;
import org.haram.rothem.exception.bodycode.RothemErrorCode;
import org.haram.rothem.exception.exception.HaramEntityExistException;
import org.haram.rothem.exception.exception.HaramEntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class AmenityMasterService {

    private final AmenityDao amenityDao;

    private final RoomAmenityDao roomAmenityDao;

    public Amenity save(Amenity amenity) {
        return amenityDao.save(amenity)
                .orElseThrow(() -> new HaramEntityExistException("이미 Amenity 가 존재합니다.", RothemErrorCode.ALREADY_EXIST_AMENITY));
    }

    public Amenity modify(Amenity amenity) {
        Amenity currentAmenity = amenityDao.findById(amenity.getAmenitySeq())
                .orElseThrow(() -> new HaramEntityNotFoundException("Amenity 가 존재하지 않습니다.", RothemErrorCode.NOT_FOUND_AMENITY));

        if (amenity.getFilePath() != null) {
            currentAmenity.setFilePath(amenity.getFilePath());
        }
        currentAmenity.setTitle(amenity.getTitle());

        return currentAmenity;
    }

    public boolean delete(List<Long> amenitySeqs) {
        try {
            List<Amenity> amenityList = amenityDao.findAllById(amenitySeqs);
            for (Amenity amenity : amenityList) {
                amenity.setStatus(false);
            }
            roomAmenityDao.deleteAllByAmenitySeqIn(amenitySeqs);
            return true;
        } catch (Exception e) {
            log.warn("[AmenityMasterService] delete -> amenity 리스트 삭제 실패, {}", amenitySeqs.toString());
            return false;
        }
    }

}
