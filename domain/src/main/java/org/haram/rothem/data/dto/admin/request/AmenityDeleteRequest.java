package org.haram.rothem.data.dto.admin.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AmenityDeleteRequest {

    private List<Long> amenitySeqs;

}
