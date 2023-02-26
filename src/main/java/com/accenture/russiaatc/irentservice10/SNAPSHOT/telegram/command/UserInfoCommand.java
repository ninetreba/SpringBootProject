package com.accenture.russiaatc.irentservice10.SNAPSHOT.telegram.command;

import com.accenture.russiaatc.irentservice10.SNAPSHOT.telegram.TelegramUserService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;

@Component
public class UserInfoCommand extends MyBotCommand {
    private final TelegramUserService telegramUserService;

    public UserInfoCommand(TelegramUserService telegramUserService) {
        super("/userinfo", "getting user info");
        this.telegramUserService = telegramUserService;
    }


    @Override
    public void executeCommand(AbsSender absSender, User user, Chat chat, String[] strings) {
        com.accenture.russiaatc.irentservice10.SNAPSHOT.model.user.User client =
                this.telegramUserService.getUser(user);

        System.out.println(client.getLogin());

        sendAnswer(absSender, chat.getId(),
                    "Ваш логин " + client.getLogin() + ".\n" +
                        "Ваш пароль " + client.getPassword() + ".\n" +
                        "Ваш баланс " + client.getBalance() + ".\n", false);
    }


}
