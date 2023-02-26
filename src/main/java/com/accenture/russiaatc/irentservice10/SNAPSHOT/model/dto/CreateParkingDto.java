package com.accenture.russiaatc.irentservice10.SNAPSHOT.model.dto;

import com.accenture.russiaatc.irentservice10.SNAPSHOT.model.Status;
import com.accenture.russiaatc.irentservice10.SNAPSHOT.model.parking.ParkingType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateParkingDto {

    private String name;

    private Double longitude;
    private Double latitude;

    private Double radius;

    private ParkingType parkingType;

    private Status status;
}
