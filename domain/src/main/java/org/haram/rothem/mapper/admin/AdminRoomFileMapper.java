package org.haram.rothem.mapper.admin;

import org.haram.rothem.data.CustomMapperConfig;
import org.haram.rothem.data.dto.admin.request.RoomFileRequest;
import org.haram.rothem.data.dto.admin.response.RoomFileResponse;
import org.haram.rothem.data.entity.RoomFile;
import org.haram.rothem.data.dto.admin.request.RoomFileRequest;
import org.haram.rothem.data.dto.admin.response.RoomFileResponse;
import org.haram.rothem.data.entity.RoomFile;
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
