package org.haram.rothem.core.bodycode;

public interface BodyCode {

    String getCode();

    String getMessage();

    default Integer getStatus() {
        return 500;
    }
}
