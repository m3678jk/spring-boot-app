package com.goit5.springweb.feature.user;

import com.goit5.springweb.feature.role.Role;
import com.goit5.springweb.feature.role.RoleService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Map;
// Чтобы наш класс стал контроллером простого названия мало.
// Нужно навешать на него аннотацию @RestController которая укажет спринг что данный класс предоставляет REST API.

@RestController
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
        ModelAndView modelAndView = new ModelAndView("user-info");
        modelAndView.addAllObjects(Map.of("list", list));
        return modelAndView;

    }

    //TODO without BindingResult doesn't work . find out why
    @GetMapping("/create-new")
    public ModelAndView showForm(UserSecurity user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView("simple-registration");
        List<Role> roles = roleService.findAll();
        modelAndView.addAllObjects(Map.of("user", user, "allRoles", roles));
        return modelAndView;
    }


    //    @ModelAttribute("roleList")
//    public List<Role> getList(){
//        return roleService.findAll();
//    }
    @PostMapping("/create-new")
    public RedirectView submitForm(@ModelAttribute("user") UserSecurity user) {
        userService.saveUser(user);
        return new RedirectView("/app/users/list");
    }

    @RequestMapping("/update")
    public ModelAndView showEditForm(@RequestParam Long id) {
        log.info("Handling update user request: " + id);
        ModelAndView modelAndView = new ModelAndView("simple-registration");
        User user = userService.findById(id);
        List<Role> roles = roleService.findAll();
        modelAndView.addAllObjects(Map.of("user", user, "allRoles", roles));
        return modelAndView;
    }

    //TODO fix cascade delete user + relation in role
    @RequestMapping("/delete/{id}") //Post and Delete does not work
    public RedirectView deleteUsers(@PathVariable Long id) {
        log.info("Handling delete user request: " + id);
        userService.deleteUser(id);
        return new RedirectView("/app/users/list");
    }
//
}
