package com.accenture.russiaatc.irentservice10.SNAPSHOT.service;

import com.accenture.russiaatc.irentservice10.SNAPSHOT.model.dto.CreateParkingDto;
import com.accenture.russiaatc.irentservice10.SNAPSHOT.model.dto.ParkingDto;
import com.accenture.russiaatc.irentservice10.SNAPSHOT.model.parking.Parking;

import java.util.List;


public interface ParkingService {
    Parking getParking(Long id);
    Parking getParkingByName(String name);
    List<Parking> getParkings();
    void createParking(CreateParkingDto createParkingDto);
    Parking updateParking(ParkingDto parkingDto);
    Parking deleteParking(Long id);
}
