package com.accenture.russiaatc.irentservice10.SNAPSHOT.controller;

import com.accenture.russiaatc.irentservice10.SNAPSHOT.mapper.UserMapper;
import com.accenture.russiaatc.irentservice10.SNAPSHOT.model.dto.UserDto;
import com.accenture.russiaatc.irentservice10.SNAPSHOT.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@AllArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;



    @GetMapping("/current")
    public UserDto getCurrentUser(){
        return userMapper.toDto(userService.getCurrentUser());
    }

    @GetMapping("/{id}")
    public UserDto getUser(@PathVariable Long id){
        return userMapper.toDto(userService.getById(id));
    }


}
