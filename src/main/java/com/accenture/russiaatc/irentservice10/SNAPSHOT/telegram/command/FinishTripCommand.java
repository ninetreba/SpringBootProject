package com.accenture.russiaatc.irentservice10.SNAPSHOT.telegram.command;

import com.accenture.russiaatc.irentservice10.SNAPSHOT.model.dto.CloseRentDto;
import com.accenture.russiaatc.irentservice10.SNAPSHOT.model.parking.Parking;
import com.accenture.russiaatc.irentservice10.SNAPSHOT.model.rent.Rent;
import com.accenture.russiaatc.irentservice10.SNAPSHOT.model.transport.Transport;
import com.accenture.russiaatc.irentservice10.SNAPSHOT.service.ParkingService;
import com.accenture.russiaatc.irentservice10.SNAPSHOT.service.TripService;
import com.accenture.russiaatc.irentservice10.SNAPSHOT.service.VehicleService;
import com.accenture.russiaatc.irentservice10.SNAPSHOT.telegram.TelegramUserService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;

@Component
public class FinishTripCommand extends MyBotCommand{
    private final TripService tripService;
    private  final TelegramUserService telegramUserService;
    private final VehicleService vehicleService;
    private final ParkingService parkingService;


    public FinishTripCommand(TripService tripService, TelegramUserService telegramUserService, VehicleService vehicleService, ParkingService parkingService) {
        super("/finishtrip", "Закончить поездку");
        this.tripService = tripService;
        this.telegramUserService = telegramUserService;
        this.vehicleService = vehicleService;
        this.parkingService = parkingService;
    }


    @Override
    public void executeCommand(AbsSender absSender, User user, Chat chat, String[] strings){
        Transport transport = vehicleService.findByNumber(strings[0]);
        Parking parking = parkingService.getParkingByName(strings[1]);

        com.accenture.russiaatc.irentservice10.SNAPSHOT.model.user.User client =
                telegramUserService.getUser(user);

        CloseRentDto closeRentDto = new CloseRentDto();
        closeRentDto.setIdTransport(transport.getId());
        closeRentDto.setIdUser(client.getId());
        closeRentDto.setIdParking(parking.getId());

        Rent rent = tripService.closeRent(closeRentDto);

        sendAnswer(absSender, chat.getId(),
                "Поездка на " + strings[0] +" завершена в " + rent.getEndRent() +
                        " и стоимость составила " + rent.getCost() + " рублей" + "\n", false);
    }


}
