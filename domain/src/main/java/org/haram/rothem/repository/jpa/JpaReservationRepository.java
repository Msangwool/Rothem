package org.haram.rothem.repository.jpa;

import org.haram.rothem.data.entity.Reservation;
import org.haram.rothem.data.type.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JpaReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findAllByUserId(String userId);

    List<Reservation> findAllByReservationStatus(ReservationStatus reservationStatus);

    Optional<Reservation> findByUserIdAndReservationStatus(String userId, ReservationStatus reservationStatus);

    boolean existsByUserIdAndReservationStatus(String userId, ReservationStatus reservationStatus);

    Optional<Reservation> findByReservationCode(String reservationCode);

}
