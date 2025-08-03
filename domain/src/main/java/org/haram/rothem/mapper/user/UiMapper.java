package org.haram.rothem.mapper.user;

import com.space.common.config.CustomMapperConfig;
import com.space.data.domain.rothem.user.response.*;
import com.space.domain.rothem.entity.Calendar;
import com.space.domain.rothem.entity.Reservation;
import com.space.domain.rothem.entity.Time;
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
