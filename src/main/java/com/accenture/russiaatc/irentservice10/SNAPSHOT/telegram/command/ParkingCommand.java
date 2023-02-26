package com.accenture.russiaatc.irentservice10.SNAPSHOT.telegram.command;

import com.accenture.russiaatc.irentservice10.SNAPSHOT.model.parking.Parking;
import com.accenture.russiaatc.irentservice10.SNAPSHOT.service.ParkingService;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;

import java.util.List;

public class ParkingCommand extends MyBotCommand {
    private final ParkingService parkingService;

    public ParkingCommand(ParkingService parkingService) {
        super("/parking", "Поиск парковки");
        this.parkingService = parkingService;
    }

    @Override
    public void executeCommand(AbsSender absSender, User user, Chat chat, String[] strings){
        List<Parking> parkings = parkingService.getParkings();

        StringBuilder sb = new StringBuilder();
        for(Parking parking : parkings){
            sb.append("\uD83C\uDD7F️ Название: ").append(parking.getName()).append(", тип: ").append(parking.getParkingType()).append("\n");
        }

        sendAnswer(absSender, chat.getId(), sb.toString(), false);
    }


}
