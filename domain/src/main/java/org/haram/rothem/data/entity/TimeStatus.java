package org.haram.rothem.data.entity;

import org.haram.rothem.data.type.ReservationType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Entity
@Table(name = "rt_time_status")
@NoArgsConstructor
@Setter
public class TimeStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "time_status_seq")
    private Long timeStatusSeq;

    @Column(name = "room_seq")
    private Long roomSeq;

    @Column(name = "calendar_seq")
    private Long calendarSeq;

    @Column(name = "time_seq")
    private Long timeSeq;

    @Column(name = "reservation_seq")
    private Long reservationSeq;

    @Column(name = "request_type")
    @Enumerated(value = EnumType.STRING)
    private ReservationType reservationType;

    @Column(name = "is_available")
    private Boolean isAvailable;


    public TimeStatus(Long roomSeq, Long calendarSeq, Long timeSeq, Long reservationSeq, ReservationType reservationType, Boolean isAvailable) {
        this.roomSeq = roomSeq;
        this.calendarSeq = calendarSeq;
        this.timeSeq = timeSeq;
        this.reservationSeq = reservationSeq;
        this.reservationType = reservationType;
        this.isAvailable = isAvailable;
    }

    public static TimeStatus of(Long roomSeq, Long calendarSeq, Long timeSeq, Long reservationSeq, ReservationType reservationType, Boolean isAvailable) {
        return new TimeStatus(roomSeq, calendarSeq, timeSeq, reservationSeq, reservationType, isAvailable);
    }

}
