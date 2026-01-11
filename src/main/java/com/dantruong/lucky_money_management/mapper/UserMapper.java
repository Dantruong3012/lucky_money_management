package com.dantruong.lucky_money_management.mapper;

import com.dantruong.lucky_money_management.model.dto.UserDto;
import com.dantruong.lucky_money_management.model.entity.User;


public class UserMapper {
    public static UserDto userToDto(User user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getDisplayName());
       return userDto;
    }
}
