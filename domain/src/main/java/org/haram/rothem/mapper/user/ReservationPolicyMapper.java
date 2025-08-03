package org.haram.rothem.mapper.user;

import com.space.common.config.CustomMapperConfig;
import com.space.data.domain.rothem.user.request.ReservationPolicyRequest;
import com.space.domain.rothem.entity.ReservationPolicy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = CustomMapperConfig.class)
public interface ReservationPolicyMapper {

    @Mapping(target = "seq", ignore = true)
    ReservationPolicy toEntity(ReservationPolicyRequest reservationPolicyRequest);

}
