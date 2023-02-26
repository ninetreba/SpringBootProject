package com.accenture.russiaatc.irentservice10.SNAPSHOT.mapper;

import com.accenture.russiaatc.irentservice10.SNAPSHOT.model.dto.RentDto;
import com.accenture.russiaatc.irentservice10.SNAPSHOT.model.rent.Rent;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ParkingMapper.class, UserMapper.class, TransportMapper.class})
public interface TripMapper {

    @Mapping(source = "startParking", target = "startParkingDto")
    @Mapping(source = "endParking", target = "endParkingDto")
    @Mapping(source = "user", target = "userDto")
    @Mapping(source = "transport", target = "transportDto")
    RentDto toDto(Rent rent);

    List<RentDto> toDtoList(List<Rent> rents);
}
