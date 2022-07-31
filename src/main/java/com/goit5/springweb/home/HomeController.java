package com.goit5.springweb.home;

import com.goit5.springweb.feature.user.User;
import com.goit5.springweb.feature.user.UserDto;
import com.goit5.springweb.feature.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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

@RestController
@AllArgsConstructor
public class HomeController {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bcryptEncoder;

    private final AuthenticationManager authenticationManager;



    @PostMapping("/register")
    public User register(@RequestBody UserDto userDto) {
        User newUser = new User();
        newUser.setEmail(userDto.getEmail());
        newUser.setPassword(bcryptEncoder.encode(userDto.getPassword()));

        return userRepository.save(newUser);
    }


    @PostMapping("/login")
    public ResponseEntity<HttpStatus> login(@RequestBody UserDto userDto) throws Exception {
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userDto.getEmail(), bcryptEncoder.encode(userDto.getPassword())));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (BadCredentialsException ex){
           throw new Exception("invalid");
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/dashboard")
    public String dashboard(){
        return "dashboard user";
    }
}
