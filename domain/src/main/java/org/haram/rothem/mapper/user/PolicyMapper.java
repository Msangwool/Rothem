package org.haram.rothem.mapper.user;

import com.space.common.config.CustomMapperConfig;
import com.space.data.domain.rothem.user.response.PolicyResponse;
import com.space.domain.rothem.entity.Policy;
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
