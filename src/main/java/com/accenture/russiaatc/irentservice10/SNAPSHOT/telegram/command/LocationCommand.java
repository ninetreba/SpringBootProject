package com.accenture.russiaatc.irentservice10.SNAPSHOT.telegram.command;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;

@Component
public class LocationCommand extends MyBotCommand {

    public LocationCommand() {
        super("/location", "Отправить локацию");
    }

    @Override
    public void executeCommand(AbsSender absSender, User user, Chat chat, String[] strings){

        sendAnswer(absSender, chat.getId(),
                "Нажмите share location, чтобы отправить локацию", true);
    }


}
