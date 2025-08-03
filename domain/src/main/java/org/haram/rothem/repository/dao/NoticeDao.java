package org.haram.rothem.repository.dao;

import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.space.domain.rothem.entity.RothemNotice;
import com.space.domain.rothem.repository.jpa.JpaNoticeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

import static com.space.domain.rothem.entity.QRothemNotice.rothemNotice;

@Component
@RequiredArgsConstructor
@Slf4j
public class NoticeDao {

    private final JPAQueryFactory jpaQueryFactory;

    private final JpaNoticeRepository jpaNoticeRepository;

    public List<RothemNotice> findAll() {
        return jpaNoticeRepository.findAll();
    }

    public Page<RothemNotice> findAll(Pageable pageable) {
        QueryResults<RothemNotice> queryResults = jpaQueryFactory.selectFrom(rothemNotice)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(rothemNotice.createdAt.desc())
                .fetchResults();
        return new PageImpl<>(queryResults.getResults(), pageable, queryResults.getTotal());
    }

    public List<RothemNotice> findAllByOrderByCreatedAtDesc() {
        return jpaNoticeRepository.findAllByOrderByCreatedAtDesc();
    }

    public Optional<RothemNotice> findById(Long noticeSeq) {
        return jpaNoticeRepository.findById(noticeSeq);
    }

    public Optional<RothemNotice> save(RothemNotice rothemNotice) {
        return Optional.of(jpaNoticeRepository.save(rothemNotice));
    }

    public void delete(List<Long> noticeSeqs) {
        jpaNoticeRepository.deleteAllById(noticeSeqs);
    }

}
