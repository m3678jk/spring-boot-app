package com.goit5.springweb.feature.user;


import java.util.List;

public interface UserService {

    void saveUser(UserSecurity userSecurity);

    void deleteUser(Long id);

    User findByEmail(String email);

    List<User> findAll();
    void updateUser(UserSecurity userSecurity, Long id);

    User findById(Long id);
}


