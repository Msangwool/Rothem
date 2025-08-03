package org.haram.rothem.data.dto.admin.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PolicyResponse {

    private Long policySeq;

    private String title;

    private String content;

    private Boolean isRequired;

    private String createdBy;

    private String createdAt;

    private String modifiedBy;

    private String modifiedAt;

}
