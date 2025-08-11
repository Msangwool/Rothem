package org.haram.rothem.mapper.user;

import org.haram.rothem.data.CustomMapperConfig;
import org.haram.rothem.data.dto.user.response.RoomFileResponse;
import org.haram.rothem.data.entity.RoomFile;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.List;

@Mapper(config = CustomMapperConfig.class)
public interface RoomFileMapper {

    @Named("toRoomFileResponse")
    RoomFileResponse toResponse(RoomFile roomFile);

    @IterableMapping(qualifiedByName = "toRoomFileResponse")
    List<RoomFileResponse> toResponses(List<RoomFile> roomFiles);

}
