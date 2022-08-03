package com.goit5.springweb.security;

import com.goit5.springweb.feature.role.Role;
import com.goit5.springweb.feature.role.RoleRepository;
import com.goit5.springweb.feature.user.User;
import com.goit5.springweb.feature.user.UserDto;
import com.goit5.springweb.feature.user.UserRepository;
import com.goit5.springweb.security.AuthChecker;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.net.http.HttpResponse;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class HomeController {

    //TODO email should be unique
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder bcryptEncoder;
    private final AuthChecker authChecker;
//    private final AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ModelAndView register(@RequestBody UserDto user) {
        User newUser = new User();
        newUser.setEmail(user.getEmail());
        newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
        newUser.setFirstName((user.getFirstName()));
        newUser.setLastName(user.getLastName());
        Optional<Role> optionalRole = roleRepository.findByName("USER");
        Role role = optionalRole.get();
        newUser.addRoles(role);
        System.out.println(newUser);
        userRepository.save(newUser);

        return new ModelAndView("home");
    }


    @PostMapping("/login")
    public ModelAndView login(@RequestBody User user) throws Exception {
//        Authentication authentication;
//        try {
//            authentication = authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(user.getEmail(), bcryptEncoder.encode(user.getPassword())));
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//        } catch (BadCredentialsException ex){
//           throw new Exception("invalid");
//        }

        return new ModelAndView("home");
    }

    @GetMapping("/dashboard")
    public ModelAndView dashboard() {
        return new ModelAndView("home");
    }

    @GetMapping("/superadmin")
    public ModelAndView superadmin() {
        if (!authChecker.hasAuthority("ADMIN")) {
            return new ModelAndView("superadmin");
        }
        return new ModelAndView("forbidden");
    }
}
