package com.goit5.springweb.feature.role;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/app/roles")
@AllArgsConstructor
@Log //  из ломбок библиотеки чтобы выводить логи.
public class RoleController {

    private final RoleService roleService;

    @GetMapping("/list")
    public ModelAndView findAll() {
        log.info("Handling find role list request");
        List<Role> list = roleService.findAll();
        ModelAndView modelAndView = new ModelAndView("role-list");
        modelAndView.addAllObjects(Map.of("list", list));
        return modelAndView;
    }

    @GetMapping("/create-new")
    public ModelAndView showForm(Role role) {
        ModelAndView modelAndView = new ModelAndView("role-new-form");
        modelAndView.addAllObjects(Map.of("role", role));
        return modelAndView;
    }

    @PostMapping("/create-new")
    public RedirectView submitForm(@ModelAttribute("role") Role role){
        roleService.saveRole(role);
        return new RedirectView("/app/roles/list");
    }

    @RequestMapping("/update")
    public ModelAndView showEditForm(@RequestParam Long id) {
        log.info("Handling update role request: " + id);
        ModelAndView modelAndView = new ModelAndView("role-new-form");
        Role role = roleService.findById(id);
        modelAndView.addAllObjects(Map.of("role", role));
        return modelAndView;
    }
    //TODO fix cascade delete user + relation in role
    @RequestMapping("/delete/{id}") //Post and Delete does not work
    public RedirectView deleteUsers(@PathVariable Long id) {
        log.info("Handling delete user request: " + id);
        roleService.deleteRole(id);
        return new RedirectView("/app/roles/list");
    }

}
