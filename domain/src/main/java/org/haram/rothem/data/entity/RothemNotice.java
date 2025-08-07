package org.haram.rothem.data.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Entity
@Table(name = "rt_notice")
@NoArgsConstructor
@Setter
public class RothemNotice extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notice_seq")
    private Long noticeSeq;

    private String title;

    private String content;

    @Column(name = "thumbnail_path")
    private String thumbnailPath;

}
