package org.haram.rothem.data.dto.admin.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CalendarCreateRequest {

    private LocalDate date;

    public void validate() {
        if (date == null) {
            throw new IllegalArgumentException("date is empty");
        }
    }

}
