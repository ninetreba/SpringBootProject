package com.accenture.russiaatc.irentservice10.SNAPSHOT.telegram;

import com.accenture.russiaatc.irentservice10.SNAPSHOT.model.user.User;

public interface TelegramUserService {
    User addTelegramUser(org.telegram.telegrambots.meta.api.objects.User user);
    User getUser(org.telegram.telegrambots.meta.api.objects.User user);
}
