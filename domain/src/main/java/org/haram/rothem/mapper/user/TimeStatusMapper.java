package org.haram.rothem.mapper.user;

import org.haram.rothem.data.CustomMapperConfig;
import org.haram.rothem.data.dto.user.request.TimeStatusRequest;
import org.haram.rothem.data.entity.TimeStatus;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = CustomMapperConfig.class)
public interface TimeStatusMapper {

    @Mapping(target = "timeStatusSeq", ignore = true)
    TimeStatus toEntity(Long roomSeq, Long calendarSeq, Long timeSeq, String requestType);

    @Mapping(target = "timeStatusSeq", ignore = true)
    TimeStatus toEntity(TimeStatusRequest timeStatusRequest);

}
