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
        User user = new User();
        System.out.println("userDto = " + userDto);
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
//        Optional<Role> optionalRole = roleRepository.findByName("USER");
//        Role role = optionalRole.get();
//        user.addRoles(role);

//        Optional<Role> name = roleRepository.findByName(String.valueOf(userDto.getAuthorities()));

        userRepository.save(user);
    }

    @Override
    public void deleteUser(String email) {

    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(()-> new UsernameNotFoundException("User not found"));
    }

    @Override
    public List<User> findAll() {
        List<User> list = userRepository.findAll();
//        System.out.println("list = " + list);
        return list;
    }
}
