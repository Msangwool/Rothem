package org.haram.rothem.repository.dao;

import com.space.domain.rothem.entity.ReservationPolicy;
import com.space.domain.rothem.repository.jpa.JpaReservationPolicyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class ReservationPolicyDao {

    private final JpaReservationPolicyRepository jpaReservationPolicyRepository;

    public List<ReservationPolicy> findAll() {
        return jpaReservationPolicyRepository.findAll();
    }

    public List<ReservationPolicy> findAllByReservationSeq(Long reservationSeq) {
        return jpaReservationPolicyRepository.findAllByReservationSeq(reservationSeq);
    }

    public Optional<ReservationPolicy> findById(Long reservationPolicySeq) {
        return jpaReservationPolicyRepository.findById(reservationPolicySeq);
    }

    public Optional<ReservationPolicy> save(ReservationPolicy reservationPolicy) {
        return Optional.of(jpaReservationPolicyRepository.save(reservationPolicy));
    }

    public Optional<Boolean> delete(ReservationPolicy reservationPolicy) {
        try {
            jpaReservationPolicyRepository.delete(reservationPolicy);
            return Optional.of(true);
        } catch (Exception e) {
            log.warn("ReservationPolicyDao - delete() : Exception -> {}, Message -> {}", e.getClass(), e.getMessage());
            return Optional.empty();
        }
    }

}
