package com.banking.microservice.userservice.domain.mapper;

import com.banking.microservice.userservice.domain.dto.UserDto;
import com.banking.microservice.userservice.domain.entities.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserMapper {

    public UserDto toUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setEmail(user.getEmail());
        userDto.setFullName(user.getFullName());
        userDto.setPhone(user.getPhone());
        userDto.setActive(user.isActive());
        userDto.setRole(user.getRole().name());
        return userDto;
    }

    public User toUser(UserDto userDto) {
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setFullName(userDto.getFullName());
        user.setPhone(userDto.getPhone());
        return user;
    }

    public List<UserDto> toListUserDto(List<User> users) {
        List<UserDto> userDtos = new ArrayList<>();
        for (User user : users) {
            UserDto newUser = this.toUserDto(user);
            userDtos.add(newUser);
        }
        return userDtos;
    }

    public List<User> toListUser(List<UserDto> userDtos) {
        return userDtos.stream().map(this::toUser).toList();
    }
}
