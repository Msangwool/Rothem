package org.haram.rothem.repository.jpa;

import org.haram.rothem.data.entity.Amenity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaAmenityRepository extends JpaRepository<Amenity, Long> {

    List<Amenity> findAllByStatusTrue();

    List<Amenity> findAllByAmenitySeqInAndStatusTrue(List<Long> amenitySeqs);

}
