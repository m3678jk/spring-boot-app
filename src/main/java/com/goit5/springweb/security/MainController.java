package com.goit5.springweb.security;

import com.goit5.springweb.feature.role.Role;
import com.goit5.springweb.feature.role.RoleService;
import com.goit5.springweb.feature.user.User;
import com.goit5.springweb.feature.user.UserRepository;
import com.goit5.springweb.feature.user.UserSecurity;
import com.goit5.springweb.feature.user.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/")
@AllArgsConstructor
@Log
public class MainController {
    private final UserService userService;
    private final RoleService roleService;
    private final UserRepository userRepository;

    @GetMapping("/")
    public ModelAndView init() {
        User firstDefaultAdmin = userService.findByEmail("admin@mail");
        Role admin = roleService.findByName("ADMIN");
        if ( !firstDefaultAdmin.getRoles().contains(admin)) {
            firstDefaultAdmin.addRoles(admin);
            userRepository.save(firstDefaultAdmin);
        }
        User firstDefaultUser = userService.findByEmail("user@mail");
        Role user = roleService.findByName("USER");
        if ( ! firstDefaultUser.getRoles().contains(user)) {
            firstDefaultUser.addRoles(user);
            userRepository.save(firstDefaultUser);
        }
        ModelAndView modelAndView = new ModelAndView("home");
        return modelAndView;

    }

    @GetMapping("/registration")
    public ModelAndView showForm(UserSecurity user, BindingResult bindingResult) { //without BindingResult doesn't work
        ModelAndView modelAndView = new ModelAndView("sign-up-form");
        modelAndView.addAllObjects(Map.of("user", user));
        return modelAndView;
    }

    @PostMapping("/registration")
    public RedirectView submitForm(@ModelAttribute("user") UserSecurity user) {

        userService.saveUser(user);
        return new RedirectView("/");
    }


}
