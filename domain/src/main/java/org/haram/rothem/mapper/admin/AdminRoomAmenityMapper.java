package org.haram.rothem.mapper.admin;

import com.space.common.config.CustomMapperConfig;
import com.space.data.domain.rothem.admin.request.RoomAmenityRequest;
import com.space.domain.rothem.entity.RoomAmenity;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(config = CustomMapperConfig.class)
public interface AdminRoomAmenityMapper {

    @Named("toRoomAmenityEntity")
    @Mapping(target = "seq", ignore = true)
    RoomAmenity toEntity(RoomAmenityRequest roomAmenityRequest);

    @IterableMapping(qualifiedByName = "toRoomAmenityEntity")
    List<RoomAmenity> toEntities(List<RoomAmenityRequest> roomAmenityRequests);
}
