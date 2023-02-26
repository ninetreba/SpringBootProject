package com.accenture.russiaatc.irentservice10.SNAPSHOT.mapper;

import com.accenture.russiaatc.irentservice10.SNAPSHOT.model.dto.UserDto;
import com.accenture.russiaatc.irentservice10.SNAPSHOT.model.user.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User model);
}
