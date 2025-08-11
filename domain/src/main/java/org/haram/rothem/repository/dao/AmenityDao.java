package org.haram.rothem.repository.dao;

import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.haram.rothem.data.entity.Amenity;
import org.haram.rothem.repository.jpa.JpaAmenityRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

import static org.haram.rothem.data.entity.QAmenity.amenity;

@Component
@RequiredArgsConstructor
@Slf4j
public class AmenityDao {

    private final JPAQueryFactory jpaQueryFactory;

    private final JpaAmenityRepository jpaAmenityRepository;

    public List<Amenity> findAll() {
        return jpaAmenityRepository.findAllByStatusTrue();
    }

    public Page<Amenity> findAll(Pageable pageable) {
        QueryResults<Amenity> queryResults = jpaQueryFactory.selectFrom(amenity)
                .where(amenity.status.eq(true))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(amenity.createdAt.desc())
                .fetchResults();
        return new PageImpl<>(queryResults.getResults(), pageable, queryResults.getTotal());
    }

    public List<Amenity> findAllById(List<Long> amenitySeqs) {
        return jpaAmenityRepository.findAllByAmenitySeqInAndStatusTrue(amenitySeqs);
    }

    public Optional<Amenity> findById(Long amenitySeq) {
        return jpaAmenityRepository.findById(amenitySeq);
    }

    public Optional<Amenity> save(Amenity amenity) {
        return Optional.of(jpaAmenityRepository.save(amenity));
    }

    public void delete(List<Long> amenitySeqs) {
        jpaAmenityRepository.deleteAllById(amenitySeqs);
    }

}
