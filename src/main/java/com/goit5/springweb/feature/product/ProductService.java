package com.goit5.springweb.feature.product;


import java.util.List;

public interface ProductService {
    void saveProduct(Product product);

    void deleteProduct(Long id);

    List<Product> findAll();

    Product findById(Long id);
}
