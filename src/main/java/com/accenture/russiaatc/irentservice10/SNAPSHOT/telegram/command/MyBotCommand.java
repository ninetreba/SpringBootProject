package com.accenture.russiaatc.irentservice10.SNAPSHOT.telegram.command;

import com.accenture.russiaatc.irentservice10.SNAPSHOT.exception.BusinessRuntimeException;
import com.accenture.russiaatc.irentservice10.SNAPSHOT.exception.ErrorCodeEnum;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Setter
public abstract class MyBotCommand extends BotCommand {

    public MyBotCommand(String commandIdentifier, String description) {
        super(commandIdentifier, description);
    }

    public void executeCommand(AbsSender absSender, User user, Chat chat, String[] strings){}

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        try {
            executeCommand(absSender, user, chat, strings);
        } catch (BusinessRuntimeException e){
            log.error("Command execute error", e);
            sendAnswer(absSender, chat.getId(), "Ошибка выполнения команды - " + e.getMessage(), false);
        } catch (Exception e){
            log.error("Command execute error", e);
            sendAnswer(absSender, chat.getId(), "Ошибка выполнения команды ", false);
        }
    }


    void sendAnswer(AbsSender absSender, Long chatId, String text, boolean showKeyboard) {
        SendMessage message = new SendMessage();
        message.enableMarkdown(true);
        message.setChatId(chatId.toString());
        message.setText(text);
        message.setParseMode(ParseMode.HTML);


        if (showKeyboard) {
            ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
            replyKeyboardMarkup.setResizeKeyboard(true);

            List<KeyboardRow> keyboardRowList = new ArrayList<>();

            KeyboardButton keyboardButton = new KeyboardButton();
            keyboardButton.setText("share location");
            keyboardButton.setRequestLocation(true);

            KeyboardRow keyboardRow = new KeyboardRow();
            keyboardRow.add(keyboardButton);

            keyboardRowList.add(keyboardRow);

            replyKeyboardMarkup.setKeyboard(keyboardRowList);
            message.setReplyMarkup(replyKeyboardMarkup);
            replyKeyboardMarkup.setOneTimeKeyboard(true);
        }

        try {
            absSender.execute(message);
        } catch (TelegramApiException e) {
            log.error("Telegram send message error", e);
            throw new BusinessRuntimeException(ErrorCodeEnum.TELEGRAM_SEND_MESSAGE_ERROR);
        }
    }



}
