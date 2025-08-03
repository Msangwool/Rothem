package org.haram.rothem.data.dto.admin.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UnavailableTimeStatusRequest {

    private Long timeSeq;

    private Boolean isChecked;

}
