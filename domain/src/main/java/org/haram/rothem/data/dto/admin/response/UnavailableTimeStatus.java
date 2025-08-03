package org.haram.rothem.data.dto.admin.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor(staticName = "of")
public class UnavailableTimeStatus {

    private Long timeSeq;

    // hour + ':' + minute
    private String time;

    private Boolean isChecked;

}
