package org.haram.rothem.core.bodycode;

import lombok.Getter;

@Getter
public enum SuccessCode implements BodyCode {
    SUCCESS("PA01", "Done"),
    WARING_PASSWD("NTC01", "사용자 정보 업데이트가 필요합니다.");

    private final String code;
    private final String message;
    private final Integer status = 200;

    SuccessCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
