package com.accenture.russiaatc.irentservice10.SNAPSHOT.telegram.command;

import com.accenture.russiaatc.irentservice10.SNAPSHOT.telegram.TelegramUserService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.User;

import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.bots.AbsSender;

@Component
public class StartCommand extends MyBotCommand {
    private final TelegramUserService telegramUserService;

    public StartCommand(TelegramUserService telegramUserService) {
        super("/start", "start using bot");
        this.telegramUserService = telegramUserService;
    }


    @Override
    public void executeCommand(AbsSender absSender, User user, Chat chat, String[] strings) {
        com.accenture.russiaatc.irentservice10.SNAPSHOT.model.user.User client =
                this.telegramUserService.addTelegramUser(user);

        sendAnswer(absSender, chat.getId(),
                "Вы зарегистрированны под логином " + client.getLogin() + ".\n" +
                        "Ваш пароль " + client.getPassword() + ".\n" +
                        "Ваш баланс " + client.getBalance() + ".\n" +
                        "Для поиска свободного транспорта используйте команду /search", false);
    }


}
