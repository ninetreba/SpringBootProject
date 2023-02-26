package com.accenture.russiaatc.irentservice10.SNAPSHOT.mapper;

import com.accenture.russiaatc.irentservice10.SNAPSHOT.model.dto.TransportDto;
import com.accenture.russiaatc.irentservice10.SNAPSHOT.model.transport.Bicycle;
import com.accenture.russiaatc.irentservice10.SNAPSHOT.model.transport.ElectricScooter;
import com.accenture.russiaatc.irentservice10.SNAPSHOT.model.transport.Transport;
import com.accenture.russiaatc.irentservice10.SNAPSHOT.model.transport.Type;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.List;


@Mapper(componentModel = "spring", uses = ParkingMapper.class)
public interface TransportMapper {
    TransportDto electricScooterToDto(ElectricScooter electricScooter);
    TransportDto bicycleToDto(Bicycle bicycle);

    default TransportDto toDto(Transport transport) {
        if (transport.getType() == Type.ELECTRIC_SCOOTER) {
            ElectricScooter electricScooter = (ElectricScooter) transport;
            return electricScooterToDto(electricScooter);
        } else {
            Bicycle bicycle = (Bicycle) transport;
            return bicycleToDto(bicycle);
        }
    }


    default List<TransportDto> toDtoList(List<Transport> transports){
        List<TransportDto> transportDtoList = new ArrayList<>();
        for (Transport transport : transports){
            transportDtoList.add(toDto(transport));
        }
        return transportDtoList;
    }



}
