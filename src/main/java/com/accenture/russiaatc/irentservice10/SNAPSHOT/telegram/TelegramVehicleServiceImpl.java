package com.accenture.russiaatc.irentservice10.SNAPSHOT.telegram;

import com.accenture.russiaatc.irentservice10.SNAPSHOT.exception.BusinessRuntimeException;
import com.accenture.russiaatc.irentservice10.SNAPSHOT.exception.ErrorCodeEnum;
import com.accenture.russiaatc.irentservice10.SNAPSHOT.model.transport.Transport;
import com.accenture.russiaatc.irentservice10.SNAPSHOT.repository.UserTelegramRepository;
import com.accenture.russiaatc.irentservice10.SNAPSHOT.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.gavaghan.geodesy.*;

import java.util.*;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class TelegramVehicleServiceImpl implements TelegramVehicleService {
    private final VehicleService vehicleService;
    private final UserTelegramRepository userTelegramRepository;


    @Override
    public List<Transport> getVehiclesTelegram(Long chatId) {

        UserTelegram userTelegram = userTelegramRepository.findById(chatId).orElseThrow(
                ()-> new BusinessRuntimeException(ErrorCodeEnum.USER_NOT_FOUND));

        if (userTelegram.getLatitude() != null){
            double userLatitude =  userTelegram.getLatitude();
            double userLongitude = userTelegram.getLongitude();

            Map<String, Double> map = new HashMap<>();

            for ( Transport transport : vehicleService.getVehicles() ) {
                map.put(transport.getNumber(), calcDistanceInMeters(userLatitude, userLongitude,
                        transport.getLatitude(), transport.getLongitude()));
            }

            map = map.entrySet().stream()
                    .sorted(Map.Entry.comparingByValue())
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                     (e1, e2) -> e1, LinkedHashMap::new));

            List<Transport> sortedTransports = new ArrayList<>();
            map.forEach((key, value) -> sortedTransports.add(vehicleService.findByNumber(key)));
            return sortedTransports;
        } else {
            return vehicleService.getVehicles();
        }

    }



    private double calcDistanceInMeters(double latitude, double longitude, double userLat, double userLon) {

        GeodeticCalculator geoCalc = new GeodeticCalculator();
        Ellipsoid reference = Ellipsoid.WGS84;
        GlobalPosition pointA = new GlobalPosition(latitude, longitude, 0.0); // Point A
        GlobalPosition userPos = new GlobalPosition(userLat, userLon, 0.0); // Point B
        return geoCalc.calculateGeodeticCurve(reference, userPos, pointA).getEllipsoidalDistance();
        // Distance between Point A and Point B
    }



}
