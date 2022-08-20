package com.goit5.springweb.feature.user;

import com.goit5.springweb.feature.role.Role;
import com.goit5.springweb.feature.role.RoleService;
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
@RequestMapping("/app/users")
@AllArgsConstructor
@Log //  из ломбок библиотеки чтобы выводить логи.
public class UserController {
    private final UserService userService;
    private final RoleService roleService;

    @GetMapping("/list")
    public ModelAndView findAll() {
        log.info("Handling find all users request");
        List<User> list = userService.findAll();
        ModelAndView modelAndView = new ModelAndView("user-list");
        modelAndView.addAllObjects(Map.of("list", list));
        return modelAndView;

    }

    @GetMapping("/create-new")
    public ModelAndView showForm(UserSecurity user, BindingResult bindingResult) { //without BindingResult doesn't work
        ModelAndView modelAndView = new ModelAndView("edit-user-form");
        List<Role> roles = roleService.findAll();
        modelAndView.addAllObjects(Map.of("user", user, "allRoles", roles));
        return modelAndView;
    }

    @PostMapping("/create-new")
    public RedirectView submitForm(@ModelAttribute("user") UserSecurity user) {
        userService.saveUser(user);
        return new RedirectView("/app/users/list");
    }

    @RequestMapping("/update")
    public ModelAndView showEditForm(@RequestParam UUID id) {
        log.info("Handling update user request: " + id);
        ModelAndView modelAndView = new ModelAndView("edit-user-form");
        User user = userService.findById(id);
        List<Role> roles = roleService.findAll();
        modelAndView.addAllObjects(Map.of("user", user, "allRoles", roles));
        return modelAndView;
    }

    @RequestMapping("/delete/{id}") //Post and Delete does not work only requestMapping
    public RedirectView deleteUsers(@PathVariable UUID id) {
        log.info("Handling delete user request: " + id);
        userService.deleteUser(id);
        return new RedirectView("/app/users/list");
    }

}
