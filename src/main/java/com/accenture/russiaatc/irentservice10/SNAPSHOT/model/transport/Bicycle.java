package com.accenture.russiaatc.irentservice10.SNAPSHOT.model.transport;

import com.accenture.russiaatc.irentservice10.SNAPSHOT.model.Status;
import com.accenture.russiaatc.irentservice10.SNAPSHOT.model.parking.Parking;
import lombok.Builder;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Builder
@Entity
@Table(name = "BICYCLE", schema = "PUBLIC")
@PrimaryKeyJoinColumn(name = "TRANSPORT_ID_TRANSPORT")
public class Bicycle extends Transport {

    public Bicycle() {}

    public Bicycle(Long id, String number, Type type, Condition condition, Status status, TransportStatus transportStatus, Double coordinateX, Double coordinateY, Parking currentParking) {
        super(id, number, type, condition, status, transportStatus, coordinateX, coordinateY, currentParking);
    }
}
