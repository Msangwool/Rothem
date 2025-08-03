package org.haram.rothem.mapper.admin;

import com.space.common.config.CustomMapperConfig;
import com.space.data.domain.rothem.admin.request.AmenityRequest;
import com.space.data.domain.rothem.admin.response.AmenityResponse;
import com.space.domain.rothem.entity.Amenity;
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