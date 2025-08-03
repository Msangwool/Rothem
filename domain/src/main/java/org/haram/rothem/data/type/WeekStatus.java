package org.haram.rothem.data.type;

import lombok.Getter;

@Getter
public enum WeekStatus {

    PAST_WEEK("과거 모든 주"),
    AVAILABLE_WEEK("예약 가능 주"),
    PENDING_WEEK("예약 대기 주"),
    ;

    private final String title;

    WeekStatus(String title) {
        this.title = title;
    }

}
