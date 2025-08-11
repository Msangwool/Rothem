package org.haram.rothem.exception.bodycode;

public interface BodyCode {

    String getCode();

    String getMessage();

    default Integer getStatus() {
        return 500;
    }
}
