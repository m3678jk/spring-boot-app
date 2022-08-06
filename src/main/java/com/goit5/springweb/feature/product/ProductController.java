package com.goit5.springweb.feature.product;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {

    @GetMapping("/product")
    public List<Product> getAllProducts(){
        return null;
    }

}
