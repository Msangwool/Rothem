package org.haram.rothem.mapper.user;

import com.space.common.config.CustomMapperConfig;
import com.space.data.domain.rothem.user.response.RoomFileResponse;
import com.space.domain.rothem.entity.RoomFile;
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
