package com.goit5.springweb.feature.producer;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.*;

@Controller
@RequestMapping("/app/producers")
@AllArgsConstructor
public class ProducerController {

    private final ProducerService producerService;

    @GetMapping("/list")
    public ModelAndView findAll() {
        Map<Producer, List<String>> list = producerService.getPrettyListForTemplate();
        ModelAndView modelAndView = new ModelAndView("producer-list");
        modelAndView.addAllObjects(Map.of("list", list));
        return modelAndView;

    }

    @GetMapping("/create-new")
    public ModelAndView showForm(Producer producer, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView("edit-producer-form");
        modelAndView.addAllObjects(Map.of("producer", producer));
        return modelAndView;
    }

    @PostMapping("/create-new")
    public RedirectView submitForm(@ModelAttribute("producer") Producer producer) {
        producerService.saveProducer(producer);
        return new RedirectView("/app/producers/list");
    }

    @RequestMapping("/update")
    public ModelAndView showEditForm(@RequestParam UUID id) {
        ModelAndView modelAndView = new ModelAndView("edit-producer-form");
        Producer producer = producerService.findById(id);

        modelAndView.addAllObjects(Map.of("producer", producer));
        return modelAndView;
    }

    @RequestMapping("/delete/{id}")
    public RedirectView deleteUsers(@PathVariable UUID id) {
        producerService.deleteProducer(id);
        return new RedirectView("/app/producers/list");
    }
}
