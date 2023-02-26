package com.accenture.russiaatc.irentservice10.SNAPSHOT.controller;

import com.accenture.russiaatc.irentservice10.SNAPSHOT.mapper.TripMapper;
import com.accenture.russiaatc.irentservice10.SNAPSHOT.model.dto.*;
import com.accenture.russiaatc.irentservice10.SNAPSHOT.service.TripService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@AllArgsConstructor
@RestController
@RequestMapping("/trips")
public class TripController {
    private final TripService tripService;
    private final TripMapper tripMapper;

    @GetMapping
    public List<RentDto> getAllRent(){
        return tripMapper.toDtoList(tripService.getAllRent());
    }

    @PostMapping
    public RentDto createRent(@RequestBody CreateRentDto createRentDto){
        return tripMapper.toDto(tripService.createRent(createRentDto));
    }

    @PutMapping
    public RentDto closeRent(@RequestBody CloseRentDto closeRentDto){
        return tripMapper.toDto(tripService.closeRent(closeRentDto));
    }


}
