package org.haram.rothem.data.dto.admin.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AmenityResponse {

    private Long amenitySeq;

    private String title;

    private String filePath;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Boolean isChecked;

}
