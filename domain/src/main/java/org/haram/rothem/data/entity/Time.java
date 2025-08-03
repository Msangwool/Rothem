package org.haram.rothem.data.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Entity
@Table(name = "rt_time")
@NoArgsConstructor
@Setter
public class Time {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "time_seq")
    private Long timeSeq;

    private String hour;

    private String minute;

    private String meridiem;

    @Column(name = "is_available")
    private Boolean isAvailable;

}
