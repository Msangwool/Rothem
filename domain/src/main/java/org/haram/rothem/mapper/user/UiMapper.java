package org.haram.rothem.mapper.user;

import org.haram.rothem.data.CustomMapperConfig;
import org.haram.rothem.data.dto.user.response.*;
import org.haram.rothem.data.entity.Calendar;
import org.haram.rothem.data.entity.Reservation;
import org.haram.rothem.data.entity.Time;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(config = CustomMapperConfig.class)
public interface UiMapper {

    AvailableTimeResponse toResponse(Time time, Boolean isReserved);

    AvailableCalendarResponse toResponse(Calendar calendar);

    AvailableCalendarResponse toResponse(Calendar calendar, List<AvailableTimeResponse> times);

    ReservationInfoResponse toResponse(Reservation reservation, RoomResponse roomResponse,
                                       CalendarResponse calendarResponse, List<TimeResponse> timeResponses);

}
