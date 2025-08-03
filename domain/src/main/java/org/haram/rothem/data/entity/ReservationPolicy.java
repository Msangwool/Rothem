package org.haram.rothem.data.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Entity
@Table(name = "rt_reservation_policy")
@NoArgsConstructor
@Setter
public class ReservationPolicy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    @Column(name = "reservation_seq")
    private Long reservationSeq;

    @Column(name = "policy_seq")
    private Long policySeq;

    @Column(name = "policy_agree_yn")
    private char policyAgreeYn;

}
