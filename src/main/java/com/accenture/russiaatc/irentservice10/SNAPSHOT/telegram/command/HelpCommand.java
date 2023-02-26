package com.accenture.russiaatc.irentservice10.SNAPSHOT.telegram.command;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;


@Component
public class HelpCommand extends MyBotCommand {

    public HelpCommand() {
        super("/help", "Помощь");
    }

    @Override
    public void executeCommand(AbsSender absSender, User user, Chat chat, String[] strings) {

        sendAnswer(absSender, chat.getId(),
                "/start - Начать работу с ботом" + ".\n" +
                        "/userinfo - Получить информацию о своем аккаунте" + ".\n" +
                        "/search - Поиск свободного транспорта" + ".\n" +
                        "/parking - Список парковок" + ".\n" +
                        "/starttrip id(рег номер ТС) - Начать поездку" + ".\n" +
                        "/finishtrip id(рег номер ТС) name(название парковки) - Закончить поездку" + ".\n" +
                        "/location - Поделиться локацией" + ".\n" +
                        "/help - Получить информацию о возможностях бота", false);
    }




}