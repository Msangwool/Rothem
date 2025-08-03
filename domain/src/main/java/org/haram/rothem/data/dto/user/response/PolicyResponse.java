package org.haram.rothem.data.dto.user.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PolicyResponse {

    private Long policySeq;

    private String title;

    private String content;

    private Boolean isRequired;

    private final String createdBy = "";

    private final String createdAt = "";

    private final String modifiedBy = "";

    private final String modifiedAt = "";

}