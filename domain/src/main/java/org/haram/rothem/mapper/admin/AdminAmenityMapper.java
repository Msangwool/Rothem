package org.haram.rothem.mapper.admin;

import org.haram.rothem.data.CustomMapperConfig;
import org.haram.rothem.data.CustomMapperConfig;
import org.haram.rothem.data.dto.admin.request.AmenityRequest;
import org.haram.rothem.data.dto.admin.response.AmenityResponse;
import org.haram.rothem.data.entity.Amenity;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(config = CustomMapperConfig.class)
public interface AdminAmenityMapper {

    @Mapping(target = "amenitySeq", ignore = true)
    Amenity toEntity(AmenityRequest amenityRequest);

    Amenity toEntity(Long amenitySeq, AmenityRequest amenityRequest);

    @Named("toAmenityResponse")
    AmenityResponse toResponse(Amenity amenity);

    AmenityResponse toResponse(Amenity amenity, boolean isChecked);

    @IterableMapping(qualifiedByName = "toAmenityResponse")
    List<AmenityResponse> toResponses(List<Amenity> amenities);

}