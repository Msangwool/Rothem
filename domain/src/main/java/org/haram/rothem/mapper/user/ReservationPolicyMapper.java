package org.haram.rothem.mapper.user;

import org.haram.rothem.data.CustomMapperConfig;
import org.haram.rothem.data.dto.user.request.ReservationPolicyRequest;
import org.haram.rothem.data.entity.ReservationPolicy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = CustomMapperConfig.class)
public interface ReservationPolicyMapper {

    @Mapping(target = "seq", ignore = true)
    ReservationPolicy toEntity(ReservationPolicyRequest reservationPolicyRequest);

}
