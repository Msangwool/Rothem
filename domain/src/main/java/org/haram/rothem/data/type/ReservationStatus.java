package org.haram.rothem.data.type;

import lombok.Getter;

@Getter
public enum ReservationStatus {

    RESERVED("예약됨"),
    USE_COMPLETE("사용완료"),
    CANCEL_USER("사용자 취소"),
    CANCEL_ADMIN("관리자 취소"),
    EXPIRED_RESERVATION("예약 만료");

    private final String title;

    ReservationStatus(String title) {
        this.title = title;
    }

}
