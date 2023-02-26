package com.accenture.russiaatc.irentservice10.SNAPSHOT.service;

import com.accenture.russiaatc.irentservice10.SNAPSHOT.exception.BusinessRuntimeException;
import com.accenture.russiaatc.irentservice10.SNAPSHOT.exception.ErrorCodeEnum;
import com.accenture.russiaatc.irentservice10.SNAPSHOT.model.dto.CreateParkingDto;
import com.accenture.russiaatc.irentservice10.SNAPSHOT.model.dto.ParkingDto;
import com.accenture.russiaatc.irentservice10.SNAPSHOT.model.parking.Parking;
import com.accenture.russiaatc.irentservice10.SNAPSHOT.model.Status;
import com.accenture.russiaatc.irentservice10.SNAPSHOT.repository.ParkingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@RequiredArgsConstructor
@Service
public class ParkingServiceImpl implements ParkingService{
    private final ParkingRepository parkingRepository;


    @Override
    public List<Parking> getParkings() {
        log.info("получение всех парковок.");
        return parkingRepository.findAllByStatus(Status.WORKING);
    }


    @Override
    public Parking getParking(Long id) {
        return parkingRepository.findById(id).orElseThrow(
                () -> new BusinessRuntimeException(ErrorCodeEnum.PARKING_NOT_FOUND)
        );
    }


    public Parking getParkingByName(String name){
        return parkingRepository.findByName(name).orElseThrow(
                () -> new BusinessRuntimeException(ErrorCodeEnum.PARKING_NOT_FOUND)
        );
    }


    @Override
    public void createParking(CreateParkingDto createParkingDto){
        Parking parking = new Parking();
        parking.setParkingType(createParkingDto.getParkingType());
        parking.setStatus(createParkingDto.getStatus());
        parking.setLongitude(createParkingDto.getLongitude());
        parking.setLatitude(createParkingDto.getLatitude());
        parking.setRadius(createParkingDto.getRadius());
        parking.setName(createParkingDto.getName());
        parkingRepository.save(parking);
    }

    @Override
    public Parking updateParking(ParkingDto parkingDto) {
        if (!isValid(parkingDto)){
            throw new BusinessRuntimeException(ErrorCodeEnum.INCORRECT_DATA);
        }
        Parking parkingOld = getParking(parkingDto.getId());
        parkingOld.setName(parkingDto.getName());
        parkingOld.setLongitude(parkingDto.getLongitude());
        parkingOld.setLatitude(parkingDto.getLatitude());
        parkingOld.setRadius(parkingDto.getRadius());
        parkingOld.setParkingType(parkingDto.getParkingType());
        parkingOld.setStatus(parkingDto.getStatus());
        parkingRepository.save(parkingOld);
        return parkingOld;
    }


    @Override
    public Parking deleteParking(Long id) {
        Parking parking = getParking(id);
        parking.setStatus(Status.DELETED);
        parkingRepository.save(parking);
        return parking;
    }


    private boolean isValid(ParkingDto parkingDto){
        if (parkingDto.getLongitude() >= 0 && parkingDto.getLatitude() >= 0 && parkingDto.getRadius() > 0
        && parkingDto.getParkingType() != null && parkingDto.getStatus() != null) {
            return true;
        }
        return false;
    }



}
