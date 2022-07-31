//package com.goit5.springweb.feature.user;
//
//import com.goit5.springweb.exception.ValidationException;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.extern.java.Log;
//import org.springframework.http.ResponseEntity;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.context.request.WebRequest;
//import org.springframework.web.servlet.ModelAndView;
//
//import java.util.List;
//// Чтобы наш класс стал контроллером простого названия мало.
//// Нужно навешать на него аннотацию @RestController которая укажет спринг что данный класс предоставляет REST API.
//
//@RestController
//@RequestMapping("/users")
//@AllArgsConstructor
//@Log //  из ломбок библиотеки чтобы выводить логи.
//public class UserController {
//    private final User.UserService userService;
//
//    @PostMapping("/save")
//    public UserDto saveUsers(@RequestBody UserDto userDto) throws ValidationException {
//        log.info("Handling save users: " + userDto);
//        return userService.saveUser(userDto);
//    }
//    @GetMapping("/registration")
//    public ModelAndView showRegistrationForm(@RequestBody UserDto userDto) {
//        ModelAndView result = new ModelAndView("user-log");
//        return result;
//    }
//
//    @GetMapping("/findAll")
//    public List<UserDto> findAllUsers() {
//        log.info("Handling find all users request");
//        return userService.findAll();
//    }
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
//}
