package org.haram.rothem.data.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor(staticName = "of")
public class TimeStatusUniqueKey {

    private Long roomSeq;

    private Long calendarSeq;

    private Long timeSeq;

    private Boolean isAvailable;

}
