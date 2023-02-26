package com.accenture.russiaatc.irentservice10.SNAPSHOT.service;

import com.accenture.russiaatc.irentservice10.SNAPSHOT.model.dto.CloseRentDto;
import com.accenture.russiaatc.irentservice10.SNAPSHOT.model.dto.CreateRentDto;
import com.accenture.russiaatc.irentservice10.SNAPSHOT.model.rent.Rent;

import java.util.List;

public interface TripService {

    Rent createRent(CreateRentDto createRentDto);
    Rent closeRent(CloseRentDto closeRentDto);
    List<Rent> getAllRent();

}
