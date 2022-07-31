package com.goit5.springweb.feature.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;


@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    private static final Map<String, String> USERS = Map.of(
            "user", "user",
            "admin", "admin"
    );
    // private final RoleRepository roleRepository;
//    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (!USERS.containsKey(username)) {
            throw new UsernameNotFoundException("User not found");
        }

        String role = username.toUpperCase();
        String password = USERS.get(username);

        return new UserDetails() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return Collections.singleton(() -> role);
            }

            @Override
            public String getPassword() {
                return password;
            }

            @Override
            public String getUsername() {
                return username;
            }

            @Override
            public boolean isAccountNonExpired() {
                return true;
            }

            @Override
            public boolean isAccountNonLocked() {
                return true;
            }

            @Override
            public boolean isCredentialsNonExpired() {
                return true;
            }

            @Override
            public boolean isEnabled() {
                return true;
            }
        };
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

