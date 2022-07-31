//package com.goit5.springweb.feature.user;
//
//import org.springframework.stereotype.Component;
//
//@Component
//public class UserConverter {
//    public User fromUserDtoToUser(UserDto userDto) {
//        User user = new User();
//        user.setId(userDto.getId());
//        user.setEmail(userDto.getEmail());
//        user.setPassword(userDto.getPassword());
//        user.setFirstName(userDto.getFirstName());
//        user.setLastName(userDto.getLastName());
//        return user;
//    }
//
//    public UserDto fromUserToUserDto(User user) {
//        return UserDto.builder()
//                .id(user.getId())
//                .email(user.getEmail())
//                .password(user.getPassword())
//                .firstName(user.getFirstName())
//                .lastName(user.getLastName())
//                .build();
//    }
//}
