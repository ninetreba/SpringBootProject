package com.accenture.russiaatc.irentservice10.SNAPSHOT.exception;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

import java.text.MessageFormat;

@AllArgsConstructor
public enum ErrorCodeEnum {
    INTERNAL_SERVER_ERROR("Внутренняя ошибка сервиса"),
    USER_NOT_FOUND("Пользователь не найден", HttpStatus.NOT_FOUND),
    PARKING_NOT_FOUND("Парковка не найдена", HttpStatus.NOT_FOUND),
    TRANSPORT_NOT_FOUND("Транспорт не найден", HttpStatus.NOT_FOUND),
    NO_MONEY("Недостаточно средств"),
    WRONG_TYPE_PARKING("Некорректный тип парковки"),
    TRANSPORT_BUSY("Транспорт занят"),
    TOO_MUCH_RENTS("Слишком много активных аренд"),
    INCORRECT_DATA("Некорректные данные"),
    TELEGRAM_SEND_MESSAGE_ERROR("Ошибка при отправке сообщения в телеграм"),
    SET_ID_TRANSPORT("Задайте id транспорта."),
    BAD_TOKEN("плохой токен");


    private final String messageTemplate;
    private HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;


    public String getMessage(Object... args){
        return MessageFormat.format(messageTemplate, args);
    }

    ErrorCodeEnum(String messageTemplate) {
        this.messageTemplate = messageTemplate;
    }

    public String getCode(){
        return this.name();
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

}
