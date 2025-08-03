package org.haram.rothem.data.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor(staticName = "of")
public class TimeUniqueKey {

    public String hour;

    public String minute;

    public String meridiem;

}
