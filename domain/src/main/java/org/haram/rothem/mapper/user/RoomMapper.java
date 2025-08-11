package org.haram.rothem.mapper.user;

import org.haram.rothem.data.CustomMapperConfig;
import org.haram.rothem.data.dto.user.response.RoomResponse;
import org.haram.rothem.data.entity.Room;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.List;

@Mapper(config = CustomMapperConfig.class)
public interface RoomMapper {

    @Named("toRoomResponse")
    RoomResponse toResponse(Room room);

    @IterableMapping(qualifiedByName = "toRoomResponse")
    List<RoomResponse> toResponses(List<Room> rooms);

}
