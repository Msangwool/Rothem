package org.haram.rothem.mapper.user;

import com.space.common.config.CustomMapperConfig;
import com.space.data.domain.rothem.user.request.ReservationRequest;
import com.space.data.domain.rothem.user.response.ReservationResponse;
import com.space.domain.rothem.entity.Reservation;
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
