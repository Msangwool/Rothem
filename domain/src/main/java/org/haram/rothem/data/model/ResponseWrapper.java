package org.haram.rothem.data.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import org.haram.rothem.core.bodycode.BodyCode;
import org.haram.rothem.data.model.config.SpringContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseWrapper<T> {

    private String code;
    private String dateTime;
    private String description;
    private T data;

    public static <T> ResponseWrapper<T> toBody(BodyCode bodyCode) {
        return ResponseWrapper.<T>builder()
                .code(bodyCode.getCode())
                .description(SpringContext.setDescriptionStatus(bodyCode.getMessage()))
                .dateTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm")))
                .data(null)
                .build();
    }

    public static <T> ResponseWrapper<T> toBody(BodyCode bodyCode, T data) {
        return ResponseWrapper.<T>builder()
                .code(bodyCode.getCode())
                .description(SpringContext.setDescriptionStatus(bodyCode.getMessage()))
                .dateTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm")))
                .data(data)
                .build();
    }

    public static <T> ResponseEntity<ResponseWrapper<T>> toResponse(BodyCode bodyCode) {
        return ResponseEntity.ok().body((toBody(bodyCode)));
    }

    public static <T> ResponseEntity<ResponseWrapper<T>> toResponse(BodyCode bodyCode, int status) {
        return ResponseEntity.status(status).body(toBody(bodyCode));
    }

    public static <T> ResponseEntity<ResponseWrapper<T>> toResponse(BodyCode bodyCode, T data) {
        return ResponseEntity.ok().body(toBody(bodyCode, data));
    }

    public static <T> ResponseEntity<ResponseWrapper<T>> toResponse(BodyCode bodyCode, T body, int status) {
        return ResponseEntity.status(status).body(toBody(bodyCode, body));
    }

    public static <T> ResponseEntity<ResponseWrapper<T>> toResponse(BodyCode bodyCode, T body, int status, HttpHeaders headers) {
        headers.setContentType(MediaType.APPLICATION_JSON);
        return ResponseEntity.status(status).headers(headers).body(toBody(bodyCode, body));
    }


}
