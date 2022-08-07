package com.goit5.springweb.security;

import com.goit5.springweb.feature.role.RoleRepository;
import com.goit5.springweb.feature.user.User;
import com.goit5.springweb.feature.user.UserRepository;
import com.goit5.springweb.feature.user.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
public class HomeController {

    //TODO email should be unique
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder bcryptEncoder;
    private final UserServiceImpl userService;
    private final AuthChecker authChecker;
//    private final AuthenticationManager authenticationManager;


    @GetMapping("/get-users")
    public ModelAndView getUser() {
        List<User> list = userService.findAll();

        ModelAndView modelAndView = new ModelAndView("user-info");
        modelAndView.addAllObjects(Map.of("list",list));

        return modelAndView;

    }
//    @PostMapping("/register")
//    public ModelAndView register(@RequestBody UserSecurity user) throws ValidationException {
////        User newUser = new User();
////        newUser.setEmail(user.getEmail());
////        newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
////        newUser.setFirstName((user.getFirstName()));
////        newUser.setLastName(user.getLastName());
////        Optional<Role> optionalRole = roleRepository.findByName("USER");
////        Role role = optionalRole.get();
////        newUser.addRoles(role);
////        System.out.println(newUser);
////        userRepository.save(newUser);
//            userService.saveUser(user);
//
//        return new ModelAndView("home");
//    }


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
