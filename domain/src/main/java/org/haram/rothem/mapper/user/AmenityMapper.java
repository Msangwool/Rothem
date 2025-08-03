package org.haram.rothem.mapper.user;

import com.space.common.config.CustomMapperConfig;
import com.space.data.domain.rothem.user.response.AmenityResponse;
import com.space.domain.rothem.entity.Amenity;
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