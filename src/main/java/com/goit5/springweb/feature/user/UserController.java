package com.goit5.springweb.feature.user;

import com.goit5.springweb.exception.ValidationException;
import com.goit5.springweb.feature.role.Role;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
// Чтобы наш класс стал контроллером простого названия мало.
// Нужно навешать на него аннотацию @RestController которая укажет спринг что данный класс предоставляет REST API.

@RestController
@RequestMapping("/app/users")
@AllArgsConstructor
@Log //  из ломбок библиотеки чтобы выводить логи.
public class UserController {
    private final UserService userService;

//    @PostMapping("/save")
//    public ModelAndView saveUser(@RequestBody UserDto user) throws ValidationException {
//        log.info("Handling save users: " + user);
//        userService.saveUser(user, role);
//        findAll();
//        return new ModelAndView("user-info");
//    }


    @GetMapping("/registration")
    public ModelAndView showRegistrationForm() {
        ModelAndView result = new ModelAndView("simple-registration");
        return result;
    }

    @GetMapping("/findAll")
    public ModelAndView  findAll() {
        log.info("Handling find all users request");
        List<User> list =userService.findAll();
        ModelAndView modelAndView = new ModelAndView("user-info");
        modelAndView.addAllObjects(Map.of("list", list));
        List<List<String>> collect = list.stream().map(it -> it.getRoles().stream().map(Role::getName).collect(Collectors.toList())).collect(Collectors.toList());
        modelAndView.addAllObjects(Map.of("collect", collect));
        System.out.println(collect);
        return modelAndView;

    }
//    @GetMapping("/findByEmail")
//    public UserDto findByLogin(@RequestParam String email) {
//        log.info("Handling find by login request: " + email);
//        return userService.findByEmail(email);
//    }
//
//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<Void> deleteUsers(@PathVariable Integer id) {
//        log.info("Handling delete user request: " + id);
//        userService.deleteUser(id);
//        return ResponseEntity.ok().build();
//    }
//
}
