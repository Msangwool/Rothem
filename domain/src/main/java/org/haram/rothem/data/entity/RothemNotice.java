package org.haram.rothem.data.entity;

import com.space.data.Auditable;
import com.space.data.entity.RothemNoticeEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Entity
@Table(name = "rt_notice")
@NoArgsConstructor
@Setter
public class RothemNotice extends Auditable implements RothemNoticeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notice_seq")
    private Long noticeSeq;

    private String title;

    private String content;

    @Column(name = "thumbnail_path")
    private String thumbnailPath;

}
