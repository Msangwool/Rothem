package org.haram.rothem.mapper.user;

import org.haram.rothem.data.CustomMapperConfig;
import org.haram.rothem.data.dto.user.response.CalendarResponse;
import org.haram.rothem.data.entity.Calendar;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.List;

@Mapper(config = CustomMapperConfig.class)
public interface CalendarMapper {

    @Named("toCalendarResponse")
    CalendarResponse toResponse(Calendar calendar);

    @IterableMapping(qualifiedByName = "toCalendarResponse")
    List<CalendarResponse> toResponses(List<Calendar> calendars);

}
