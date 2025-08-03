package org.haram.rothem.mapper.user;

import com.space.common.config.CustomMapperConfig;
import com.space.data.domain.rothem.user.response.RoomResponse;
import com.space.domain.rothem.entity.Room;
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
