package org.haram.rothem.mapper.admin;

import org.haram.rothem.data.CustomMapperConfig;
import org.haram.rothem.data.dto.admin.request.CalendarModifyRequest;
import org.haram.rothem.data.dto.admin.request.CalendarRequest;
import org.haram.rothem.data.dto.admin.response.CalendarResponse;
import org.haram.rothem.data.entity.Calendar;
import org.haram.rothem.data.entity.CalendarUniqueKey;
import org.haram.rothem.data.type.WeekStatus;
import org.haram.rothem.util.CalendarUtil;
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
