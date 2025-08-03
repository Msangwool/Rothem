package org.haram.rothem.data.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ReservationType {

    RESERVED("reserved", "예약됨"),
    UNAVAILABLE("unavailable", "사용불가"),
    HOLIDAY("holiday", "공휴일"),
    ;

    private final String key;

    private final String title;

}
