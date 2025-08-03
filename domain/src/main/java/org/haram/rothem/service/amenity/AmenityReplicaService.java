package org.haram.rothem.service.amenity;

import com.space.domain.rothem.entity.Amenity;
import com.space.domain.rothem.repository.dao.AmenityDao;
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
public class AmenityReplicaService {

    private final AmenityDao amenityDao;

    public List<Amenity> findAll() {
        return amenityDao.findAll();
    }

    public Page<Amenity> findAll(Pageable pageable) {
        return amenityDao.findAll(pageable);
    }

    public List<Amenity> findAllById(List<Long> amenitySeqs) {
        return amenityDao.findAllById(amenitySeqs);
    }

    public Amenity findById(Long amenitySeq) {
        return amenityDao.findById(amenitySeq)
                .orElseThrow(() -> new SpaceEntityNotFoundException("Amenity 가 존재하지 않습니다.", RothemErrorCode.NOT_FOUND_AMENITY));
    }

}
