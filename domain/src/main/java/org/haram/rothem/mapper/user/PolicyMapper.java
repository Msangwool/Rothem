package org.haram.rothem.mapper.user;

import org.haram.rothem.data.CustomMapperConfig;
import org.haram.rothem.data.dto.admin.response.PolicyResponse;
import org.haram.rothem.data.entity.Policy;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.List;

@Mapper(config = CustomMapperConfig.class)
public interface PolicyMapper {

    @Named("toPolicyResponse")
    PolicyResponse toResponse(Policy policy);

    @IterableMapping(qualifiedByName = "toPolicyResponse")
    List<PolicyResponse> toResponses(List<Policy> policies);

}
