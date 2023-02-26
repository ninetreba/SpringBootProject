package com.accenture.russiaatc.irentservice10.SNAPSHOT.telegram;

import com.accenture.russiaatc.irentservice10.SNAPSHOT.repository.UserTelegramRepository;
import com.accenture.russiaatc.irentservice10.SNAPSHOT.service.ParkingService;
import com.accenture.russiaatc.irentservice10.SNAPSHOT.service.TripService;
import com.accenture.russiaatc.irentservice10.SNAPSHOT.service.VehicleService;
import com.accenture.russiaatc.irentservice10.SNAPSHOT.telegram.command.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Location;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


@Component
public class TelegramBot extends TelegramLongPollingCommandBot {
    private final TelegramUserService telegramUserService;
    private final VehicleService vehicleService;
    private final TripService tripService;
    private final ParkingService parkingService;
    private final TelegramVehicleService telegramVehicleService;
    private final UserTelegramRepository userTelegramRepository;

    @Value("${bot.name}")
    private String botUsername;

    @Value("${bot.token}")
    private String botToken;


    public TelegramBot(TelegramUserService telegramUserService, VehicleService vehicleService, TripService tripService, ParkingService parkingService, TelegramVehicleService telegramVehicleService,  UserTelegramRepository userTelegramRepository) {
        this.telegramUserService = telegramUserService;
        this.vehicleService = vehicleService;
        this.tripService = tripService;
        this.parkingService = parkingService;
        this.telegramVehicleService = telegramVehicleService;
        this.userTelegramRepository = userTelegramRepository;
        this.register(new UserInfoCommand(telegramUserService));
        this.register(new StartCommand(telegramUserService));
        this.register(new SearchVehicleCommand(vehicleService, telegramVehicleService));
        this.register(new ParkingCommand(parkingService));
        this.register(new StartTripCommand(tripService, telegramUserService, vehicleService));
        this.register(new FinishTripCommand(tripService, telegramUserService, vehicleService, parkingService));
        this.register(new HelpCommand());
        this.register(new LocationCommand());
    }




    @Override
    public void processNonCommandUpdate(Update update) {

        if (update.hasMessage()) {
            Message message = update.getMessage();
            if (message.hasLocation()) {

                Location location = message.getLocation();

                UserTelegram userTelegram = userTelegramRepository.findById(message.getChatId()).orElseThrow();
                userTelegram.setLatitude(location.getLatitude().doubleValue());
                userTelegram.setLongitude(location.getLongitude().doubleValue());
                userTelegramRepository.save(userTelegram);

                SendMessage sendLocationMessage = new SendMessage();
                sendLocationMessage.setText("Локация получена");
                sendLocationMessage.setParseMode(ParseMode.HTML);
                sendLocationMessage.setChatId(message.getChatId().toString());
                try {
                    execute(sendLocationMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                return;
            }
        }


            String command = update.getMessage().getText().substring(1);
            String chatId = update.getMessage().getChatId().toString();
            String text = "Некорректная команда " + command + ". Чтобы узнать доступные команды, наберите /help";

            SendMessage sm = new SendMessage();
            sm.setChatId(chatId);
            sm.setText(text);

            try {
                execute(sm);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }


    }


    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }


}