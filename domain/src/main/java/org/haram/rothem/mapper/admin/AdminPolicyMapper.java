package org.haram.rothem.mapper.admin;

import com.space.common.config.CustomMapperConfig;
import com.space.data.domain.rothem.admin.request.PolicyRequest;
import com.space.data.domain.rothem.admin.response.PolicyResponse;
import com.space.domain.rothem.entity.Policy;
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
