package org.haram.rothem.data.dto.admin.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.haram.rothem.data.type.ReservationType;

@Getter
@Setter
@AllArgsConstructor(staticName = "of")
public class TimeStatusRequest {

    private Long roomSeq;

    private Long calendarSeq;

    private Long timeSeq;

    private ReservationType reservationType;

    private Boolean isAvailable;

}
