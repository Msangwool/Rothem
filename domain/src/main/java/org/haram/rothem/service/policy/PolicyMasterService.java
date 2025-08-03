package org.haram.rothem.service.policy;

import com.space.domain.rothem.entity.Policy;
import com.space.domain.rothem.repository.dao.PolicyDao;
import com.space.exception.bodycode.RothemErrorCode;
import com.space.exception.space.SpaceEntityExistException;
import com.space.exception.space.SpaceEntityNotFoundException;
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
                .orElseThrow(() -> new SpaceEntityExistException("이미 Policy 가 존재합니다.", RothemErrorCode.ALREADY_EXIST_POLICY));
    }

    public Policy modify(Policy policy) {
        Policy currentPolicy = policyDao.findById(policy.getPolicySeq())
                .orElseThrow(() -> new SpaceEntityNotFoundException("Policy 가 존재하지 않습니다.", RothemErrorCode.NOT_FOUND_POLICY));

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
