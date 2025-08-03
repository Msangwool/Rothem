package org.haram.rothem.data.entity;

import com.space.data.Auditable;
import com.space.data.type.rothem.ReservationStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Entity
@Table(name = "rt_reservation")
@NoArgsConstructor
@Setter
public class Reservation extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_seq")
    private Long reservationSeq;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "phone_num")
    private String phoneNum;

    @Column(name = "reservation_code")
    private String reservationCode;

    @Column(name = "reservation_status")
    @Enumerated(value = EnumType.STRING)
    private ReservationStatus reservationStatus;

}
