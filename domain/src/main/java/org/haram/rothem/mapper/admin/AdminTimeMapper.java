package org.haram.rothem.mapper.admin;

import org.haram.rothem.data.CustomMapperConfig;
import org.haram.rothem.data.dto.admin.response.TimeResponse;
import org.haram.rothem.data.entity.Time;
import org.haram.rothem.data.dto.admin.response.TimeResponse;
import org.haram.rothem.data.entity.Time;
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
