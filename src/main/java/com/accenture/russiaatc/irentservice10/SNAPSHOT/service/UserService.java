package com.accenture.russiaatc.irentservice10.SNAPSHOT.service;

import com.accenture.russiaatc.irentservice10.SNAPSHOT.model.user.User;


public interface UserService {
    User getCurrentUser();
    User getById(Long id);
    void save(User user);
}
