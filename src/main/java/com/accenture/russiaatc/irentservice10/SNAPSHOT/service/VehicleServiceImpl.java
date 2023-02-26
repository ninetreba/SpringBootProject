package com.accenture.russiaatc.irentservice10.SNAPSHOT.service;

import com.accenture.russiaatc.irentservice10.SNAPSHOT.exception.BusinessRuntimeException;
import com.accenture.russiaatc.irentservice10.SNAPSHOT.exception.ErrorCodeEnum;
import com.accenture.russiaatc.irentservice10.SNAPSHOT.model.Status;
import com.accenture.russiaatc.irentservice10.SNAPSHOT.model.dto.CreateTransportDto;
import com.accenture.russiaatc.irentservice10.SNAPSHOT.model.parking.Parking;
import com.accenture.russiaatc.irentservice10.SNAPSHOT.model.parking.ParkingType;
import com.accenture.russiaatc.irentservice10.SNAPSHOT.model.transport.*;
import com.accenture.russiaatc.irentservice10.SNAPSHOT.repository.ParkingRepository;
import com.accenture.russiaatc.irentservice10.SNAPSHOT.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class VehicleServiceImpl implements VehicleService {
    private final VehicleRepository vehicleRepository;
    private final ParkingRepository parkingRepository;



    public List<Transport> getVehicles(){
        log.info("Получение всех транспортов.");
        return vehicleRepository.findByStatus(Status.WORKING);
    }


    @Override
    public Transport getById(Long id){
        log.info("получение транспорта с id = [{}] ", id);
        return vehicleRepository.findById(id).orElseThrow(
                () -> new BusinessRuntimeException(ErrorCodeEnum.TRANSPORT_NOT_FOUND)
        );
    }


    public Transport findByNumber(String number){
        return vehicleRepository.findByNumber(number).orElseThrow(
                () -> new BusinessRuntimeException(ErrorCodeEnum.TRANSPORT_NOT_FOUND)
        );
    }


    public void save(Transport transport){
        vehicleRepository.save(transport);
    }


    public Transport deleteTransport(Long id){
        log.info("удаление транспорта.");
        Transport transport = vehicleRepository.findById(id).orElseThrow( () -> new BusinessRuntimeException(ErrorCodeEnum.TRANSPORT_NOT_FOUND));
        transport.setStatus(Status.DELETED);
        vehicleRepository.save(transport);
        return transport;
    }


    public Transport createTransport(CreateTransportDto createTransportDto){
        Parking parking = parkingRepository.getById(createTransportDto.getCurrentParking().getId());

        if (createTransportDto.getType() == Type.ELECTRIC_SCOOTER && parking.getParkingType() == ParkingType.BICYCLE_ONLY
                || createTransportDto.getType() == Type.BICYCLE && parking.getParkingType() == ParkingType.ELECTRIC_SCOOTER_ONLY){
            throw new BusinessRuntimeException(ErrorCodeEnum.WRONG_TYPE_PARKING);
        }

        if (createTransportDto.getType() == Type.ELECTRIC_SCOOTER){
            ElectricScooter electricScooter = new ElectricScooter();

            electricScooter.setType(createTransportDto.getType());
            electricScooter.setStatus(Status.WORKING);

            electricScooter.setLongitude(createTransportDto.getLongitude());
            electricScooter.setLatitude(createTransportDto.getLatitude());

            electricScooter.setCondition(createTransportDto.getCondition());
            electricScooter.setCurrentParking(parking);

            electricScooter.setBattery(createTransportDto.getBattery());
            electricScooter.setMaxSpeed(createTransportDto.getMaxSpeed());
            electricScooter.setTransportStatus(createTransportDto.getTransportStatus());
            vehicleRepository.save(electricScooter);
            electricScooter.setNumber("ЭСМ-" + electricScooter.getId());
            vehicleRepository.save(electricScooter);
            log.info("создание самоката.");
            return (Transport) electricScooter;
        }
        Bicycle bicycle = new Bicycle();

        bicycle.setLongitude(createTransportDto.getLongitude());
        bicycle.setLatitude(createTransportDto.getLatitude());

        bicycle.setTransportStatus(createTransportDto.getTransportStatus());
        bicycle.setType(createTransportDto.getType());
        bicycle.setStatus(Status.WORKING);
        bicycle.setCondition(createTransportDto.getCondition());
        bicycle.setCurrentParking(parking);
        vehicleRepository.save(bicycle);
        bicycle.setNumber("ВЕЛ-" + bicycle.getId());
        vehicleRepository.save(bicycle);
        log.info("создание велосипеда.");
        return (Transport) bicycle;
    }


    public List<Transport> findByType(Type type){
        return vehicleRepository.findByType(type);
    }


    public List<Transport> findByParking(Long id){
        return vehicleRepository.findByCurrentParking_Id(id);
    }


    public List<Transport> findByStatus(Status status){
        return vehicleRepository.findByStatus(status);
    }


    public List<Transport> findByTransportStatusAndStatus(TransportStatus transportStatus, Status status){
        return vehicleRepository.findByTransportStatusAndStatus(transportStatus, status);
    }


}
