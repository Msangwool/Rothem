package org.haram.rothem.data.dto.user.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReservationPolicyRequest {

    private Long policySeq;

    private char policyAgreeYn;

    private Long reservationSeq;

}
