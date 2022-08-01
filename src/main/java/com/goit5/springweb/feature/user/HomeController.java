package com.goit5.springweb.feature.user;

import com.goit5.springweb.feature.role.Role;
import com.goit5.springweb.feature.role.RoleRepository;
import com.goit5.springweb.feature.user.User;
import com.goit5.springweb.feature.user.UserDto;
import com.goit5.springweb.feature.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.Collections;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class HomeController {

    //TODO email should be unique
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    private final PasswordEncoder bcryptEncoder;
//    private final AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public User register(@RequestBody UserDto user) {
        User newUser = new User();
        newUser.setEmail(user.getEmail());
        newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
        newUser.setFirstName((user.getFirstName()));
        newUser.setLastName(user.getLastName());
        Optional<Role> optionalRole = roleRepository.findByName("USER");
        Role role = optionalRole.get();
        newUser.addRoles(role);
        System.out.println(newUser);
        return userRepository.save(newUser);
    }


//    @PostMapping("/login")
//    public ResponseEntity<HttpStatus> login(@RequestBody User user) throws Exception {
////        Authentication authentication;
////        try {
////            authentication = authenticationManager.authenticate(
////                    new UsernamePasswordAuthenticationToken(user.getEmail(), bcryptEncoder.encode(user.getPassword())));
////            SecurityContextHolder.getContext().setAuthentication(authentication);
////        } catch (BadCredentialsException ex){
////           throw new Exception("invalid");
////        }
//
//        return new ResponseEntity<>(HttpStatus.OK);
//    }

    @GetMapping("/dashboard")
    public String dashboard(){
        return "dashboard user";
    }
}
