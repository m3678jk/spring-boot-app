package com.goit5.springweb.feature.product;

import com.goit5.springweb.feature.producer.Producer;
import com.goit5.springweb.feature.producer.ProducerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/app/products")
@AllArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final ProducerService producerService;

    @GetMapping("/list")
    public ModelAndView findAll() {
        ModelAndView modelAndView = new ModelAndView("product-list");
        List<Product> list = productService.findAll();
        modelAndView.addAllObjects(Map.of("list", list));
        return modelAndView;
    }

    @GetMapping("/create-new")
    public ModelAndView showForm(Product product, BindingResult bindingResult) {

        ModelAndView modelAndView = new ModelAndView("edit-product-form");

        List<Producer> allProducers = producerService.findAll();
        modelAndView.addAllObjects(Map.of("product", product, "allProducers", allProducers));
        return modelAndView;
    }

    @PostMapping("/create-new")
    public RedirectView submitForm(@ModelAttribute("product") Product product) {
        productService.saveProduct(product);
        return new RedirectView("/app/products/list");
    }

    @RequestMapping("/update")
    public ModelAndView showEditForm(@RequestParam UUID id) {
        ModelAndView modelAndView = new ModelAndView("edit-product-form");
        Product product = productService.findById(id);
        List<Producer> producers = producerService.findAll();
        modelAndView.addAllObjects(Map.of("product", product, "allProducers", producers));
        return modelAndView;
    }

    @RequestMapping("/delete/{id}")
    public RedirectView deleteProduct(@PathVariable UUID id) {
        productService.deleteProduct(id);
        return new RedirectView("/app/products/list");
    }

}
