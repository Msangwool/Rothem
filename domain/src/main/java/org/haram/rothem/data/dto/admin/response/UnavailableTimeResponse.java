package org.haram.rothem.data.dto.admin.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor(staticName = "of")
public class UnavailableTimeResponse {

    private Long calendarSeq;

    private Long roomSeq;

    private List<UnavailableTimeStatus> timeStatuses;

}
