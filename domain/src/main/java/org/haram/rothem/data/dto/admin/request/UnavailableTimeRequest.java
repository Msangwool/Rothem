package org.haram.rothem.data.dto.admin.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UnavailableTimeRequest {

    private Long calendarSeq;

    private Long roomSeq;

    private List<UnavailableTimeStatusRequest> timeStatuses;

}
