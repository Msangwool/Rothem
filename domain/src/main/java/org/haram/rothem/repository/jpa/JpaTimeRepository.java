package org.haram.rothem.repository.jpa;

import org.haram.rothem.data.entity.Time;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JpaTimeRepository extends JpaRepository<Time, Long> {

    List<Time> findAllByIsAvailable(boolean isAvailable);

    Optional<Time> findByHourAndMinuteAndMeridiem(String hour, String minute, String meridiem);

}
