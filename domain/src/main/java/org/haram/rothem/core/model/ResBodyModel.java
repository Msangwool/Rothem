package org.haram.rothem.core.model;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lombok.Builder;
import lombok.Getter;
import org.haram.rothem.core.bodycode.BodyCode;

/**
 * Response Body 기본 베이스 코드이다.
 */
@Getter
public class ResBodyModel {
    private final String code;
    private final String description;
    private final String dateTime;
    private final Object data;

    @Builder
    public ResBodyModel(String code, String description, String dateTime, Object data) {
        this.code = code;
        this.description = description;
        this.dateTime = dateTime;
        this.data = data;
    }

    public static ResBodyModel toBody(BodyCode bodyCode) {
        return ResBodyModel.builder()
                .code(bodyCode.getCode())
                .description(bodyCode.getMessage())
                .dateTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm")))
                .data(null)
                .build();
    }

    public static ResBodyModel toBody(BodyCode bodyCode, Object data) {
        return ResBodyModel.builder()
                .code(bodyCode.getCode())
                .description(bodyCode.getMessage())
                .dateTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")))
                .data(data)
                .build();
    }
}