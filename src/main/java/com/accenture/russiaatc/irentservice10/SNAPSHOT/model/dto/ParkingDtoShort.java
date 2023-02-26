package com.accenture.russiaatc.irentservice10.SNAPSHOT.model.dto;

import com.accenture.russiaatc.irentservice10.SNAPSHOT.model.parking.ParkingType;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ParkingDtoShort {
    private Long id;
    private String name;
    private ParkingType parkingType;

}
