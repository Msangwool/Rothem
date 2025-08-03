package org.haram.rothem.mapper.admin;

import com.space.common.config.CustomMapperConfig;
import com.space.data.domain.rothem.admin.request.ReservationRequest;
import com.space.data.domain.rothem.admin.response.*;
import com.space.domain.rothem.entity.Calendar;
import com.space.domain.rothem.entity.Reservation;
import com.space.domain.rothem.entity.Time;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(config = CustomMapperConfig.class)
public interface AdminReservationMapper {

    @Mapping(target = "reservationSeq", ignore = true)
    @Mapping(source = "adminId", target = "userId")
    Reservation toEntity(ReservationRequest reservationRequest);

    @Named("toReservationResponse")
    @Mapping(target = "reservationStatus", expression = "java(reservation.getReservationStatus().getTitle())")
    ReservationResponse toResponse(Reservation reservation);

    @IterableMapping(qualifiedByName = "toReservationResponse")
    List<ReservationResponse> toResponses(List<Reservation> reservations);

    AvailableTimeResponse toResponse(Time time, Boolean isReserved);

    AvailableCalendarResponse toResponse(Calendar calendar);

    AvailableCalendarResponse toResponse(Calendar calendar, List<AvailableTimeResponse> times);

}
