package org.haram.rothem.data.entity;

import com.space.data.Auditable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;

@Getter
@Entity
@DynamicInsert
@Table(name = "rt_room")
@NoArgsConstructor
@Setter
public class Room extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_seq")
    private Long roomSeq;

    @Column(name = "thumbnail_path")
    private String thumbnailPath;

    @Column(name = "room_name")
    private String roomName;

    @Column(name = "room_explanation")
    private String roomExplanation;

    private String location;

    @Column(name = "people_count")
    private int peopleCount;

    @Column(name = "sort_num")
    private int sortNum;

    private Boolean status;

}
