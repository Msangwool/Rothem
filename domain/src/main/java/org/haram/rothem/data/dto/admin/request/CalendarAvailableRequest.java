package org.haram.rothem.data.dto.admin.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor(staticName = "of")
public class CalendarAvailableRequest {

    private Long calendarSeq;

    private Boolean isAvailable;

}
