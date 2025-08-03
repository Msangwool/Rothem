package org.haram.rothem.repository.dao;

import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.space.domain.rothem.entity.Policy;
import com.space.domain.rothem.repository.jpa.JpaPolicyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

import static com.space.domain.rothem.entity.QPolicy.policy;

@Component
@RequiredArgsConstructor
@Slf4j
public class PolicyDao {

    private final JPAQueryFactory jpaQueryFactory;

    private final JpaPolicyRepository jpaPolicyRepository;

    public List<Policy> findAll() {
        return jpaQueryFactory.selectFrom(policy)
                .where(policy.status.eq(true))
                .orderBy(policy.isRequired.desc())
                .fetch();
    }

    public Page<Policy> findAll(Pageable pageable) {
        QueryResults<Policy> queryResults = jpaQueryFactory.selectFrom(policy)
                .where(policy.status.eq(true))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(policy.createdAt.desc())
                .fetchResults();
        return new PageImpl<>(queryResults.getResults(), pageable, queryResults.getTotal());
    }

    public List<Policy> findAllByIsRequiredTrueAndStatusTrue() {
        return jpaPolicyRepository.findAllByIsRequiredTrueAndStatusTrue();
    }

    public List<Policy> findAllById(List<Long> policySeqs) {
        return jpaPolicyRepository.findAllByPolicySeqInAndStatusTrue(policySeqs);
    }

    public Optional<Policy> findById(Long policySeq) {
        return jpaPolicyRepository.findByPolicySeqAndStatusTrue(policySeq);
    }

    public Optional<Policy> save(Policy policy) {
        return Optional.of(jpaPolicyRepository.save(policy));
    }

    public void delete(List<Long> policySeqs) {
        jpaPolicyRepository.deleteAllById(policySeqs);
    }

}
