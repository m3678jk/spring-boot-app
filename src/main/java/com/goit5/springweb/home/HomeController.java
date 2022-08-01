package com.goit5.springweb.home;

import com.goit5.springweb.feature.role.Role;
import com.goit5.springweb.feature.user.User;
import com.goit5.springweb.feature.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.Collections;

@RestController
@AllArgsConstructor
public class HomeController {
    private final UserRepository userRepository;
    private final PasswordEncoder bcryptEncoder;
//    private final AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        User newUser = new User();
        System.out.println(user.toString());
        newUser.setEmail(user.getEmail());
        System.out.println("bcryptEncoder.encode(user.getPassword()) = " + user.getPassword());
        newUser.setPassword(user.getPassword());
        newUser.setFirstName((user.getFirstName()));
        newUser.setFirstName((user.getLastName()));
        Role role = new Role();
        role.setName("USER");
        newUser.addRoles(role);
        System.out.println(newUser);
        return userRepository.save(newUser);
    }


    @PostMapping("/login")
    public ResponseEntity<HttpStatus> login(@RequestBody User user) throws Exception {
//        Authentication authentication;
//        try {
//            authentication = authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(user.getEmail(), bcryptEncoder.encode(user.getPassword())));
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//        } catch (BadCredentialsException ex){
//           throw new Exception("invalid");
//        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/dashboard")
    public String dashboard(){
        return "dashboard user";
    }
}
