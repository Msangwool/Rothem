package org.haram.rothem.mapper.admin;

import com.space.common.config.CustomMapperConfig;
import com.space.data.domain.rothem.admin.request.CalendarModifyRequest;
import com.space.data.domain.rothem.admin.request.CalendarRequest;
import com.space.data.domain.rothem.admin.response.CalendarResponse;
import com.space.data.type.rothem.WeekStatus;
import com.space.domain.rothem.entity.Calendar;
import com.space.domain.rothem.entity.CalendarUniqueKey;
import com.space.domain.rothem.util.CalendarUtil;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(config = CustomMapperConfig.class, imports = CalendarUtil.class)
public interface AdminCalendarMapper {

    @Mapping(target = "calendarSeq", ignore = true)
    Calendar toEntity(CalendarRequest calendarRequest);

    @Mapping(target = "calendarSeq", ignore = true)
    @Mapping(
            target = "calendarYmd",
            expression = "java(CalendarUtil.createCalendarYmd(calendarUniqueKey.getYear(), calendarUniqueKey.getMonth(), calendarUniqueKey.getDate()))"
    )
    Calendar toEntity(CalendarUniqueKey calendarUniqueKey, Boolean isAvailable, WeekStatus weekStatus);

    Calendar toEntity(CalendarModifyRequest calendarModifyRequest);

    @Named("toCalendarResponse")
    CalendarResponse toResponse(Calendar calendar);

    @IterableMapping(qualifiedByName = "toCalendarResponse")
    List<CalendarResponse> toResponses(List<Calendar> calendars);

}
