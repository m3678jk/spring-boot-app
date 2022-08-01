package com.goit5.springweb.feature.user;

import com.goit5.springweb.exception.ValidationException;

import java.util.List;

public interface UserService {

    UserDto saveUser(UserDto userDto) throws ValidationException;

    void deleteUser(long userId);

    UserDto findByEmail(String email);

    List<UserDto> findAll();
}


