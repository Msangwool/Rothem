package org.haram.rothem.mapper.user;

import com.space.common.config.CustomMapperConfig;
import com.space.data.domain.rothem.user.response.TimeResponse;
import com.space.domain.rothem.entity.Time;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.List;

@Mapper(config = CustomMapperConfig.class)
public interface TimeMapper {

    @Named("toTimeResponse")
    TimeResponse toResponse(Time time);

    @IterableMapping(qualifiedByName = "toTimeResponse")
    List<TimeResponse> toResponses(List<Time> times);

}
