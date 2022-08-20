package com.goit5.springweb.feature.user;

import com.goit5.springweb.feature.role.Role;
import com.goit5.springweb.feature.role.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final RoleRepository roleRepository;

    @Override
    public void saveUser(UserSecurity userSecurity) {
        List<Role> usersDtoRoles = userSecurity.getRoles();

        if (userSecurity.getId() != null) {
            User user = userRepository.findById(userSecurity.getId()).orElseThrow(() -> new UsernameNotFoundException("User not found"));

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

            user.setEmail(userSecurity.getEmail());
            user.setLastName(userSecurity.getLastName());
            user.setPassword(passwordEncoder.encode(userSecurity.getPassword()));
            user.setFirstName(userSecurity.getFirstName());

            userRepository.save(user);
        } else {
            User newUser = new User();
            if (usersDtoRoles !=null) {
                for (Role r : usersDtoRoles) {
                    newUser.addRoles(r);
                }
            } else {

                Role userRole = roleRepository.findByName("USER").orElseThrow(() -> new UsernameNotFoundException("Role not found"));
                newUser.addRoles(userRole);
            }
            newUser.setEmail(userSecurity.getEmail());
            newUser.setLastName(userSecurity.getLastName());
            newUser.setPassword(passwordEncoder.encode(userSecurity.getPassword()));
            newUser.setFirstName(userSecurity.getFirstName());
            userRepository.save(newUser);
        }


    }

    @Override
    public void deleteUser(UUID id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        List<Role> roles = user.getRoles();
        List<Role> reserve = List.copyOf(roles);
        for (int i = 0; i < reserve.size(); i++) { // does not work with for each loop and without copy of real list
            Role role = reserve.get(i);
            user.removeRoles(role);
        }
        userRepository.delete(user);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Override
    public List<User> findAll() {
        List<User> list = userRepository.findAll();
        return list;
    }

    @Override
    public void updateUser(UserSecurity userSecurity, UUID id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        user.setEmail(userSecurity.getEmail());
        user.setLastName(user.getLastName());
        user.setPassword(userSecurity.getPassword());
        user.setFirstName(user.getFirstName());

        List<Role> usersRoles = user.getRoles();
        List<Role> usersDtoRoles = userSecurity.getRoles();
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
    public User findById(UUID id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return user;
    }
}
