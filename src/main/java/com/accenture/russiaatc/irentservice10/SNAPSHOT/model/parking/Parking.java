package com.accenture.russiaatc.irentservice10.SNAPSHOT.model.parking;


import com.accenture.russiaatc.irentservice10.SNAPSHOT.model.Status;
import com.accenture.russiaatc.irentservice10.SNAPSHOT.model.transport.Transport;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "Parking", schema = "PUBLIC")
public class Parking {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "HIBERNATE_SEQUENCE_PARKING")
    @Column(name = "ID_PARKING")
    private Long id;

    private String name;

    private Double longitude;
    private Double latitude;

    private Double radius;

    @Enumerated(EnumType.STRING)
    @Column(name = "PARKING_TYPE")
    private ParkingType parkingType;

    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(mappedBy = "currentParking")
    private List<Transport> transports;

    public Parking(){}


}
