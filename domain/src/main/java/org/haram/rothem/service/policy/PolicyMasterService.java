package org.haram.rothem.service.policy;

import org.haram.rothem.data.entity.Policy;
import org.haram.rothem.repository.dao.PolicyDao;
import org.haram.rothem.exception.bodycode.RothemErrorCode;
import org.haram.rothem.exception.exception.HaramEntityExistException;
import org.haram.rothem.exception.exception.HaramEntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class PolicyMasterService {

    private final PolicyDao policyDao;

    public Policy save(Policy policy) {
        return policyDao.save(policy)
                .orElseThrow(() -> new HaramEntityExistException("이미 Policy 가 존재합니다.", RothemErrorCode.ALREADY_EXIST_POLICY));
    }

    public Policy modify(Policy policy) {
        Policy currentPolicy = policyDao.findById(policy.getPolicySeq())
                .orElseThrow(() -> new HaramEntityNotFoundException("Policy 가 존재하지 않습니다.", RothemErrorCode.NOT_FOUND_POLICY));

        currentPolicy.setTitle(policy.getTitle());
        currentPolicy.setContent(policy.getContent());
        currentPolicy.setIsRequired(policy.getIsRequired());

        return currentPolicy;
    }

    public boolean delete(List<Long> policySeqs) {
        try {
            List<Policy> policyList = policyDao.findAllById(policySeqs);
            for (Policy policy : policyList) {
                policy.setStatus(false);
            }
            return true;
        } catch (Exception e) {
            log.warn("[PolicyMasterService] delete -> policy 리스트 삭제 실패, {}", policySeqs.toString());
            return false;
        }
    }

}
