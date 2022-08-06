package com.goit5.springweb.feature.user;

import com.goit5.springweb.exception.ValidationException;
import com.goit5.springweb.feature.role.Role;
import com.goit5.springweb.feature.role.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Override
    public void saveUser(UserDto userDto) throws ValidationException {
        List<Role> usersDtoRoles = userDto.getRoles();

        if (userDto.getId() != 0) {
            User user = userRepository.findById(userDto.getId()).orElseThrow(() -> new UsernameNotFoundException("User not found"));

            List<Role> usersRoles = user.getRoles();

            for (Role r : usersDtoRoles) {
                if (!usersRoles.contains(r)) {
                    user.addRoles(r);
                }
            }
            for (Role r : usersRoles) {
                if (!usersDtoRoles.contains(r)) {
                    user.removeRoles(r);
                }
            }

            user.setEmail(userDto.getEmail());
            user.setLastName(userDto.getLastName());
            user.setPassword(userDto.getPassword());
            user.setFirstName(userDto.getFirstName());

            userRepository.save(user);
        } else {
            User newUser = new User();

            for (Role r : usersDtoRoles) {
                newUser.addRoles(r);
            }
            newUser.setEmail(userDto.getEmail());
            newUser.setLastName(userDto.getLastName());
            newUser.setPassword(userDto.getPassword());
            newUser.setFirstName(userDto.getFirstName());
            userRepository.save(newUser);
        }


    }

    @Override
    public void deleteUser(Long id) {

        User user = userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        userRepository.delete(user);

    }


    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Override
    public List<User> findAll() {
        List<User> list = userRepository.findAll();
//        System.out.println("list = " + list);
        return list;
    }

    @Override
    public void updateUser(UserDto userDto, Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        user.setEmail(userDto.getEmail());
        user.setLastName(user.getLastName());
        user.setPassword(userDto.getPassword());
        user.setFirstName(user.getFirstName());

        List<Role> usersRoles = user.getRoles();
        List<Role> usersDtoRoles = userDto.getRoles();
        for (Role r : usersDtoRoles) {
            if (!usersRoles.contains(r)) {
                user.addRoles(r);
            }
        }
        for (Role r : usersRoles) {
            if (!usersDtoRoles.contains(r)) {
                user.removeRoles(r);
            }
        }

        userRepository.save(user);
    }

    @Override
    public User findById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return user;
    }
}
