package org.haram.rothem.mapper.user;

import org.haram.rothem.data.CustomMapperConfig;
import org.haram.rothem.data.dto.user.response.AmenityResponse;
import org.haram.rothem.data.entity.Amenity;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.List;

@Mapper(config = CustomMapperConfig.class)
public interface AmenityMapper {

    @Named("toAmenityResponse")
    AmenityResponse toResponse(Amenity amenity);

    AmenityResponse toResponse(Amenity amenity, boolean isChecked);

    @IterableMapping(qualifiedByName = "toAmenityResponse")
    List<AmenityResponse> toResponses(List<Amenity> amenities);

}