package org.haram.rothem.repository.jpa;

import org.haram.rothem.data.entity.RothemNotice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaNoticeRepository extends JpaRepository<RothemNotice, Long> {

    List<RothemNotice> findAllByOrderByCreatedAtDesc();

}
