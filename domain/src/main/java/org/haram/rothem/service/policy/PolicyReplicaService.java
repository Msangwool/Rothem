package org.haram.rothem.service.policy;

import org.haram.rothem.data.entity.Policy;
import org.haram.rothem.repository.dao.PolicyDao;
import org.haram.rothem.exception.bodycode.RothemErrorCode;
import org.haram.rothem.exception.exception.HaramEntityNotFoundException;
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
public class PolicyReplicaService {

    private final PolicyDao policyDao;

    public List<Policy> findAll() {
        return policyDao.findAll();
    }

    public List<Policy> findAllIsRequiredTrue() {
        return policyDao.findAllByIsRequiredTrueAndStatusTrue();
    }

    public Page<Policy> findAll(Pageable pageable) {
        return policyDao.findAll(pageable);
    }

    public Policy findById(Long policySeq) {
        return policyDao.findById(policySeq)
                .orElseThrow(() -> new HaramEntityNotFoundException("Policy 가 존재하지 않습니다.", RothemErrorCode.NOT_FOUND_POLICY));
    }

}
