package org.haram.rothem.mapper.admin;

import org.haram.rothem.data.CustomMapperConfig;
import org.haram.rothem.data.dto.admin.request.ReservationRequest;
import org.haram.rothem.data.dto.admin.response.*;
import org.haram.rothem.data.entity.Calendar;
import org.haram.rothem.data.entity.Reservation;
import org.haram.rothem.data.entity.Time;
import org.haram.rothem.data.dto.admin.request.ReservationRequest;
import org.haram.rothem.data.dto.admin.response.AvailableCalendarResponse;
import org.haram.rothem.data.dto.admin.response.AvailableTimeResponse;
import org.haram.rothem.data.dto.admin.response.ReservationResponse;
import org.haram.rothem.data.entity.Calendar;
import org.haram.rothem.data.entity.Reservation;
import org.haram.rothem.data.entity.Time;
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
