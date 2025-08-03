package org.haram.rothem.data.dto.user.request;

import com.space.data.type.rothem.ReservationType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

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
