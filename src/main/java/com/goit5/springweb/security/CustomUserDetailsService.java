package com.goit5.springweb.security;

import com.goit5.springweb.feature.user.User;
import com.goit5.springweb.feature.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user= userRepository.findByEmail(email).orElseThrow(()-> new UsernameNotFoundException("User not found"));

        return SecurityUser.fromUser(user);
    }


//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        User user = userRepository.findByEmail(email);
//
//        if (user == null) {
//            throw new UsernameNotFoundException("User not found");
//        }
//
//        return user;
//    }


//    public UserDto saveUser(UserDto userDto) throws ValidationException {
//
////        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
////        user.setRoles(new HashSet<>(roleRepository.findAll()));
////        userRepository.save(user);
//        User userFromDB = userRepository.findByEmail(userDto.getEmail());
//
//        if (userFromDB != null) {
//            return userConverter.fromUserToUserDto(userFromDB);
//        }
//        User user = userConverter.fromUserDtoToUser(userDto);
//        user.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
//        user.setRoles(new HashSet<>(roleRepository.findAll()));
//
//        User savedUser = userRepository.save(userConverter.fromUserDtoToUser(userDto));
//
//        return userConverter.fromUserToUserDto(savedUser);
//
//    }
//
//    public void deleteUser(long userId) {
//        this.userRepository.deleteById(userId);
//    }
//
//    public UserDto findByEmail(String email) {
//        User user = userRepository.findByEmail(email);
//        return user != null ? this.userConverter.fromUserToUserDto(user) : null;
//    }
//
//    public List<UserDto> findAll() {
//        return userRepository.findAll()
//                .stream()
//                .map((it) -> userConverter.fromUserToUserDto(it))
//                .collect(Collectors.toList());
//    }
//
//    private void validateUserDto(UserDto userDto) throws ValidationException {
//        if (Objects.isNull(userDto)) {
//            throw new ValidationException("Object user is null");
//        } else if (Objects.isNull(userDto.getEmail()) || userDto.getEmail().isEmpty()) {
//            throw new ValidationException("Email is empty");
//        }
//    }


}

