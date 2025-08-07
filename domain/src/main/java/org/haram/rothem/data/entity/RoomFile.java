package org.haram.rothem.data.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Entity
@Table(name = "rt_room_file")
@NoArgsConstructor
@Setter
public class RoomFile extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    @Column(name = "room_seq")
    private Long roomSeq;

    @Column(name = "sort_num")
    private int sortNum;

    @Column(name = "file_path")
    private String filePath;

}
