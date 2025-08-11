package org.haram.rothem.mapper.user;

import org.haram.rothem.data.CustomMapperConfig;
import org.haram.rothem.data.dto.user.request.ReservationRequest;
import org.haram.rothem.data.dto.user.response.ReservationResponse;
import org.haram.rothem.data.entity.Reservation;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(config = CustomMapperConfig.class)
public interface ReservationMapper {

    @Mapping(target = "reservationSeq", ignore = true)
    Reservation toEntity(ReservationRequest reservationRequest);

    @Named("toReservationResponse")
    @Mapping(target = "reservationStatus", expression = "java(reservation.getReservationStatus().getTitle())")
    ReservationResponse toResponse(Reservation reservation);

    @IterableMapping(qualifiedByName = "toReservationResponse")
    List<ReservationResponse> toResponses(List<Reservation> reservations);

}
