package com.accenture.russiaatc.irentservice10.SNAPSHOT.controller;

import com.accenture.russiaatc.irentservice10.SNAPSHOT.mapper.TransportMapper;
import com.accenture.russiaatc.irentservice10.SNAPSHOT.model.Status;
import com.accenture.russiaatc.irentservice10.SNAPSHOT.model.dto.CreateTransportDto;
import com.accenture.russiaatc.irentservice10.SNAPSHOT.model.dto.TransportDto;
import com.accenture.russiaatc.irentservice10.SNAPSHOT.model.transport.Type;
import com.accenture.russiaatc.irentservice10.SNAPSHOT.service.VehicleService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@AllArgsConstructor
@RestController
@RequestMapping("/vehicles")
public class VehicleController {
    private final VehicleService vehicleService;
    private final TransportMapper transportMapper;


    @GetMapping
    public List<TransportDto> getVehicles(){
        return transportMapper.toDtoList(vehicleService.getVehicles());
    }

    @GetMapping("/{id}")
    public TransportDto getVehicle(@PathVariable Long id){
        return transportMapper.toDto(vehicleService.getById(id));
    }

    @PostMapping
    public TransportDto createTransport(@RequestBody CreateTransportDto createTransportDto){
        return transportMapper.toDto(vehicleService.createTransport(createTransportDto));
    }

    @DeleteMapping("/{id}")
    public TransportDto deleteParking(@PathVariable Long id){
        return transportMapper.toDto(vehicleService.deleteTransport(id));
    }

    @GetMapping("/type/{type}")
    public List<TransportDto> findByType(@PathVariable Type type){
        return transportMapper.toDtoList(vehicleService.findByType(type));
    }

    @GetMapping("/parking/{id}")
    public List<TransportDto> findByParking(@PathVariable Long id){
        return transportMapper.toDtoList(vehicleService.findByParking(id));
    }

    @GetMapping("/status/{status}")
    public List<TransportDto> findByParking(@PathVariable Status status){
        return transportMapper.toDtoList(vehicleService.findByStatus(status));
    }


}
