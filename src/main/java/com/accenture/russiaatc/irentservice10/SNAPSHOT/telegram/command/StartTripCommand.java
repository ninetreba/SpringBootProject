package com.accenture.russiaatc.irentservice10.SNAPSHOT.telegram.command;

import com.accenture.russiaatc.irentservice10.SNAPSHOT.exception.BusinessRuntimeException;
import com.accenture.russiaatc.irentservice10.SNAPSHOT.exception.ErrorCodeEnum;
import com.accenture.russiaatc.irentservice10.SNAPSHOT.model.dto.CreateRentDto;
import com.accenture.russiaatc.irentservice10.SNAPSHOT.model.rent.Rent;
import com.accenture.russiaatc.irentservice10.SNAPSHOT.model.transport.Transport;
import com.accenture.russiaatc.irentservice10.SNAPSHOT.service.TripService;
import com.accenture.russiaatc.irentservice10.SNAPSHOT.service.VehicleService;
import com.accenture.russiaatc.irentservice10.SNAPSHOT.telegram.TelegramUserService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;

@Component
public class StartTripCommand  extends MyBotCommand {
    private final TripService tripService;
    private  final TelegramUserService telegramUserService;
    private final VehicleService vehicleService;

    public StartTripCommand(TripService tripService, TelegramUserService telegramUserService, VehicleService vehicleService) {
        super("/starttrip", "Начать поездку");
        this.tripService = tripService;
        this.telegramUserService = telegramUserService;
        this.vehicleService = vehicleService;
    }


    @Override
    public void executeCommand(AbsSender absSender, User user, Chat chat, String[] strings){
        if (strings.length == 0){
            throw new BusinessRuntimeException(ErrorCodeEnum.SET_ID_TRANSPORT);
        }

        Transport transport = vehicleService.findByNumber(strings[0]);

        com.accenture.russiaatc.irentservice10.SNAPSHOT.model.user.User client =
                telegramUserService.getUser(user);

        CreateRentDto createRentDto = new CreateRentDto();
        createRentDto.setIdTransport(transport.getId());
        createRentDto.setIdUser(client.getId());

        Rent rent = tripService.createRent(createRentDto);

        sendAnswer(absSender, chat.getId(),
                "Поездка на " + strings[0] +" начата в " + rent.getStartRent() + "\n", false);
    }


}
