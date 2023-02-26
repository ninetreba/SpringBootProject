package com.accenture.russiaatc.irentservice10.SNAPSHOT.repository;

import com.accenture.russiaatc.irentservice10.SNAPSHOT.telegram.UserTelegram;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserTelegramRepository extends JpaRepository<UserTelegram, Long> {

}
