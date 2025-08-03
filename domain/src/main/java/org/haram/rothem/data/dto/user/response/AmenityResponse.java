package org.haram.rothem.data.dto.user.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AmenityResponse {

    private Long amenitySeq;

    private String title;

    private String filePath;

    private Boolean isChecked;

    private final String createdBy = "";

    private final String createdAt = "";

    private final String modifiedBy = "";

    private final String modifiedAt = "";

}