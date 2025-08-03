package org.haram.rothem.mapper.admin;

import com.space.common.config.CustomMapperConfig;
import com.space.data.domain.rothem.admin.request.RoomRequest;
import com.space.data.domain.rothem.admin.response.AmenityResponse;
import com.space.data.domain.rothem.admin.response.RoomDetailResponse;
import com.space.data.domain.rothem.admin.response.RoomResponse;
import com.space.domain.rothem.entity.Room;
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
