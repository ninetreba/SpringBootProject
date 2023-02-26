package com.accenture.russiaatc.irentservice10.SNAPSHOT.controller;

import com.accenture.russiaatc.irentservice10.SNAPSHOT.mapper.ParkingMapper;
import com.accenture.russiaatc.irentservice10.SNAPSHOT.model.dto.CreateParkingDto;
import com.accenture.russiaatc.irentservice10.SNAPSHOT.model.dto.ParkingDto;
import com.accenture.russiaatc.irentservice10.SNAPSHOT.service.ParkingService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/parkings")
public class ParkingController {
    private final ParkingService parkingService;
    private final ParkingMapper parkingMapper;


    @GetMapping
    public List<ParkingDto> getParkings(){
        return parkingMapper.toDTOList(parkingService.getParkings());
    }

    @PutMapping
    public ParkingDto updateParking(@RequestBody ParkingDto parkingDto){
        return parkingMapper.toDto(parkingService.updateParking(parkingDto));
    }

    @PostMapping
    public void createParking(@RequestBody CreateParkingDto parking){
        parkingService.createParking(parking);
    }

    @DeleteMapping("/{id}")
    public ParkingDto deleteParking(@PathVariable Long id){
        return parkingMapper.toDto(parkingService.deleteParking(id));
    }


}
