package org.haram.rothem.mapper.user;

import com.space.common.config.CustomMapperConfig;
import com.space.data.domain.rothem.user.request.TimeStatusRequest;
import com.space.domain.rothem.entity.TimeStatus;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = CustomMapperConfig.class)
public interface TimeStatusMapper {

    @Mapping(target = "timeStatusSeq", ignore = true)
    TimeStatus toEntity(Long roomSeq, Long calendarSeq, Long timeSeq, String requestType);

    @Mapping(target = "timeStatusSeq", ignore = true)
    TimeStatus toEntity(TimeStatusRequest timeStatusRequest);

}
