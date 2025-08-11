package org.haram.rothem.mapper.admin;

import org.haram.rothem.data.CustomMapperConfig;
import org.haram.rothem.data.dto.admin.request.TimeStatusRequest;
import org.haram.rothem.data.dto.admin.response.TimeStatusResponse;
import org.haram.rothem.data.entity.TimeStatus;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(config = CustomMapperConfig.class)
public interface AdminTimeStatusMapper {

    @Mapping(target = "timeStatusSeq", ignore = true)
    TimeStatus toEntity(Long roomSeq, Long calendarSeq, Long timeSeq, String requestType);

    @Named("toTimeStatus")
    @Mapping(target = "timeStatusSeq", ignore = true)
    TimeStatus toEntity(TimeStatusRequest timeStatusRequest);

    @IterableMapping(qualifiedByName = "toTimeStatus")
    List<TimeStatus> toEntities(List<TimeStatusRequest> timeStatusRequestList);

    TimeStatusResponse toResponse(TimeStatus timeStatus);

}
