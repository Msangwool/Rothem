package org.haram.rothem.data.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Entity
@Table(name = "rt_room_amenity")
@NoArgsConstructor
@Setter
public class RoomAmenity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    @Column(name = "amenity_seq")
    private Long amenitySeq;

    @Column(name = "room_seq")
    private Long roomSeq;

}
