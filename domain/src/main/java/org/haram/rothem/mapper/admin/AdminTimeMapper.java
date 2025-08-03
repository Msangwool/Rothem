package org.haram.rothem.mapper.admin;

import com.space.common.config.CustomMapperConfig;
import com.space.data.domain.rothem.admin.response.TimeResponse;
import com.space.domain.rothem.entity.Time;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.List;

@Mapper(config = CustomMapperConfig.class)
public interface AdminTimeMapper {

    @Named("toTimeResponse")
    TimeResponse toResponse(Time time);

    @IterableMapping(qualifiedByName = "toTimeResponse")
    List<TimeResponse> toResponses(List<Time> times);

}
