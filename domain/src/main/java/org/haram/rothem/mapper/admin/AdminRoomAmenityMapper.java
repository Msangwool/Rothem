package org.haram.rothem.mapper.admin;

import org.haram.rothem.data.CustomMapperConfig;
import org.haram.rothem.data.dto.admin.request.RoomAmenityRequest;
import org.haram.rothem.data.entity.RoomAmenity;
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
