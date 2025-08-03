package org.haram.rothem.mapper.admin;

import com.space.common.config.CustomMapperConfig;
import com.space.data.domain.rothem.admin.request.RoomFileRequest;
import com.space.data.domain.rothem.admin.response.RoomFileResponse;
import com.space.domain.rothem.entity.RoomFile;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(config = CustomMapperConfig.class)
public interface AdminRoomFileMapper {

    @Named("toRoomEntity")
    @Mapping(target = "seq", ignore = true)
    RoomFile toEntity(RoomFileRequest roomFileRequest);

//    @IterableMapping(qualifiedByName = "toRoomEntity")
//    List<RoomFile> toEntities(List<RoomFileRequest> roomFileRequests);

    @Named("toRoomFileResponse")
    RoomFileResponse toResponse(RoomFile roomFile);

    @IterableMapping(qualifiedByName = "toRoomFileResponse")
    List<RoomFileResponse> toResponses(List<RoomFile> roomFiles);

}
