package com.accenture.russiaatc.irentservice10.SNAPSHOT.mapper;

import com.accenture.russiaatc.irentservice10.SNAPSHOT.model.dto.ParkingDto;
import com.accenture.russiaatc.irentservice10.SNAPSHOT.model.parking.Parking;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ParkingMapper {
    ParkingDto toDto(Parking parking);
    List<ParkingDto> toDTOList(List<Parking> parkings);

}
