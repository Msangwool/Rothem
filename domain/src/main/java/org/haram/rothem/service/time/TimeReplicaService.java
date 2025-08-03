package org.haram.rothem.service.time;

import com.space.domain.rothem.entity.Time;
import com.space.domain.rothem.entity.TimeStatusUniqueKey;
import com.space.domain.rothem.entity.TimeUniqueKey;
import com.space.domain.rothem.repository.dao.TimeDao;
import com.space.domain.rothem.repository.dao.TimeStatusDao;
import com.space.exception.bodycode.RothemErrorCode;
import com.space.exception.space.SpaceEntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TimeReplicaService {

    private final TimeDao timeDao;
    private final TimeStatusDao timeStatusDao;

    public List<Time> findAll() {
        return timeDao.findAll();
    }

    public Page<Time> findAll(Pageable pageable) {
        return timeDao.findAll(pageable);
    }

    public List<Time> findAllByAvailable() {
        return timeDao.findAllByIsAvailable(true);
    }

    public Time findById(Long timeSeq) {
        return timeDao.findById(timeSeq)
                .orElseThrow(() -> new SpaceEntityNotFoundException("Time 이 존재하지 않습니다.", RothemErrorCode.NOT_FOUND_TIME));
    }

    public Optional<Time> findByTimeUniqueKey(TimeUniqueKey timeUniqueKey) {
        return timeDao.findByTimeUniqueKey(timeUniqueKey);
    }

    public boolean existedByTimeStatusUniqueKey(TimeStatusUniqueKey timeStatusUniqueKey) {
        return timeStatusDao.existedByTimeStatusUniqueKey(timeStatusUniqueKey);
    }
}
