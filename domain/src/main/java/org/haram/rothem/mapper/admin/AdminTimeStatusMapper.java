package org.haram.rothem.mapper.admin;

import com.space.common.config.CustomMapperConfig;
import com.space.data.domain.rothem.admin.request.TimeStatusRequest;
import com.space.data.domain.rothem.admin.response.TimeStatusResponse;
import com.space.domain.rothem.entity.TimeStatus;
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
