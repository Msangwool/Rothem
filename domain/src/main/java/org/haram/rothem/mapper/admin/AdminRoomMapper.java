package org.haram.rothem.mapper.admin;

import org.haram.rothem.data.CustomMapperConfig;
import org.haram.rothem.data.dto.admin.request.RoomRequest;
import org.haram.rothem.data.dto.admin.response.AmenityResponse;
import org.haram.rothem.data.dto.admin.response.RoomDetailResponse;
import org.haram.rothem.data.dto.admin.response.RoomResponse;
import org.haram.rothem.data.entity.Room;
import org.haram.rothem.data.dto.admin.request.RoomRequest;
import org.haram.rothem.data.dto.admin.response.AmenityResponse;
import org.haram.rothem.data.dto.admin.response.RoomDetailResponse;
import org.haram.rothem.data.dto.admin.response.RoomResponse;
import org.haram.rothem.data.entity.Room;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(config = CustomMapperConfig.class)
public interface AdminRoomMapper {

    @Mapping(target = "roomSeq", ignore = true)
    Room toEntity(RoomRequest roomRequest);

    Room toEntity(Long roomSeq,RoomRequest roomRequest);

    @Named("toRoomResponse")
    RoomResponse toResponse(Room room);

    @IterableMapping(qualifiedByName = "toRoomResponse")
    List<RoomResponse> toResponses(List<Room> rooms);

    RoomDetailResponse toResponse(RoomResponse roomResponse, List<AmenityResponse> amenityResponses);

}
