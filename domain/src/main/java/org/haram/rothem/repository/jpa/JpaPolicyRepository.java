package org.haram.rothem.repository.jpa;

import org.haram.rothem.data.entity.Policy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JpaPolicyRepository extends JpaRepository<Policy, Long> {

    Optional<Policy> findByPolicySeqAndStatusTrue(Long policySeq);

    List<Policy> findAllByPolicySeqInAndStatusTrue(List<Long> policySeqs);

    List<Policy> findAllByIsRequiredTrueAndStatusTrue();

}
