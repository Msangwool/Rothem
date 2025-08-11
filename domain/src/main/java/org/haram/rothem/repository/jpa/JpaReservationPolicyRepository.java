package org.haram.rothem.repository.jpa;

import org.haram.rothem.data.entity.ReservationPolicy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaReservationPolicyRepository extends JpaRepository<ReservationPolicy, Long> {

    List<ReservationPolicy> findAllByReservationSeq(Long reservationSeq);

}
