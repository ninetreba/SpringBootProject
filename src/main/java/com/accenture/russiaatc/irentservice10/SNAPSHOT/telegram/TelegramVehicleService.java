package com.accenture.russiaatc.irentservice10.SNAPSHOT.telegram;

import com.accenture.russiaatc.irentservice10.SNAPSHOT.model.transport.Transport;

import java.util.List;

public interface TelegramVehicleService {
    List<Transport> getVehiclesTelegram(Long chatId);
}
