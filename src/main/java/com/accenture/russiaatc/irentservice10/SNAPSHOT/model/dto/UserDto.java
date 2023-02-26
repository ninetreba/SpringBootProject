package com.accenture.russiaatc.irentservice10.SNAPSHOT.model.dto;

import com.accenture.russiaatc.irentservice10.SNAPSHOT.model.user.Role;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


@Getter
@Setter
public class UserDto {
    private Long id;
    private String login;
    private BigDecimal balance;
    private Role role;

    public UserDto(){}

}
