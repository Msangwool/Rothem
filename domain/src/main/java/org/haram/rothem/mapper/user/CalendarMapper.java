package org.haram.rothem.mapper.user;

import com.space.common.config.CustomMapperConfig;
import com.space.data.domain.rothem.user.response.CalendarResponse;
import com.space.domain.rothem.entity.Calendar;
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
