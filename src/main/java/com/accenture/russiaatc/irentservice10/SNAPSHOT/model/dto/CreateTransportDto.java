package com.accenture.russiaatc.irentservice10.SNAPSHOT.model.dto;

import com.accenture.russiaatc.irentservice10.SNAPSHOT.model.transport.Condition;
import com.accenture.russiaatc.irentservice10.SNAPSHOT.model.transport.TransportStatus;
import com.accenture.russiaatc.irentservice10.SNAPSHOT.model.transport.Type;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateTransportDto {
    private Type type;
    private Condition condition;
    private int battery;
    private int maxSpeed;
    private TransportStatus transportStatus;

    private Double longitude;
    private Double latitude;

    private ParkingDto currentParking;
}
