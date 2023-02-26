package com.accenture.russiaatc.irentservice10.SNAPSHOT.repository;

import com.accenture.russiaatc.irentservice10.SNAPSHOT.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByLogin(String login);
}
