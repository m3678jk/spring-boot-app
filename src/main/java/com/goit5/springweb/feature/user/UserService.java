package com.goit5.springweb.feature.user;

import com.goit5.springweb.exception.ValidationException;

import java.util.List;

public interface UserService {

    void saveUser(UserDto userDto) throws ValidationException;

    void deleteUser(String email);

    User findByEmail(String email);

    List<User> findAll();
}


