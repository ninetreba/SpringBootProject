package com.accenture.russiaatc.irentservice10.SNAPSHOT.telegram;

import com.accenture.russiaatc.irentservice10.SNAPSHOT.exception.BusinessRuntimeException;
import com.accenture.russiaatc.irentservice10.SNAPSHOT.exception.ErrorCodeEnum;
import com.accenture.russiaatc.irentservice10.SNAPSHOT.model.Status;
import com.accenture.russiaatc.irentservice10.SNAPSHOT.model.user.Role;
import com.accenture.russiaatc.irentservice10.SNAPSHOT.model.user.User;
import com.accenture.russiaatc.irentservice10.SNAPSHOT.repository.UserRepository;
import com.accenture.russiaatc.irentservice10.SNAPSHOT.repository.UserTelegramRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Random;

@RequiredArgsConstructor
@Service
public class TelegramUserServiceImpl implements TelegramUserService {
    private final UserRepository userRepository;
    private final UserTelegramRepository userTelegramRepository;

    @Value("${balance.start}")
    private  BigDecimal startBalance;


    @Override
    public User addTelegramUser(org.telegram.telegrambots.meta.api.objects.User user) {
        String userName = (user.getUserName() != null) ? user.getUserName() :
                String.format("%s %s", user.getLastName(), user.getFirstName());

        Random random = new Random();

        UserTelegram userTelegram = new UserTelegram();
        userTelegram.setId(user.getId());
        userTelegramRepository.save(userTelegram);


        if (!userRepository.findByLogin(userName).isPresent()) {
            User userEntity = new User();
            userEntity.setBalance(startBalance);
            userEntity.setPassword(String.valueOf(random.ints(10201,(19211+1)).findFirst().getAsInt()));
            userEntity.setLogin(userName);
            userEntity.setStatus(Status.WORKING);
            userEntity.setRole(Role.USER);
            userRepository.save(userEntity);
            return userEntity;
        } else {
            return userRepository.findByLogin(userName).orElseThrow(
                    ()-> new BusinessRuntimeException(ErrorCodeEnum.USER_NOT_FOUND)
            );
        }


    }



    @Override
    public User getUser(org.telegram.telegrambots.meta.api.objects.User user){
        String userName = (user.getUserName() != null) ? user.getUserName() :
                String.format("%s %s", user.getLastName(), user.getFirstName());

        return userRepository.findByLogin(userName).orElseThrow();
    }




}
