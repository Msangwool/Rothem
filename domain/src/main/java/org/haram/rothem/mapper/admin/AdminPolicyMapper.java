package org.haram.rothem.mapper.admin;

import org.haram.rothem.data.CustomMapperConfig;
import org.haram.rothem.data.dto.admin.request.PolicyRequest;
import org.haram.rothem.data.dto.admin.response.PolicyResponse;
import org.haram.rothem.data.entity.Policy;
import org.haram.rothem.data.dto.admin.request.PolicyRequest;
import org.haram.rothem.data.dto.admin.response.PolicyResponse;
import org.haram.rothem.data.entity.Policy;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(config = CustomMapperConfig.class)
public interface AdminPolicyMapper {

    @Mapping(target = "policySeq", ignore = true)
    Policy toEntity(PolicyRequest policyRequest);

    Policy toEntity(Long policySeq, PolicyRequest policyRequest);

    @Named("toPolicyResponse")
    PolicyResponse toResponse(Policy policy);

    @IterableMapping(qualifiedByName = "toPolicyResponse")
    List<PolicyResponse> toResponses(List<Policy> policies);

}
