package com.ogawalucas.class04pracitce04.service;

import com.ogawalucas.class04practice04.model.Product;
import java.util.List;


public class ProductService {
    
    public List<Product> getAll() {
        return List.of(
                new Product(1, "Computer"),
                new Product(2, "Monitor"),
                new Product(3, "Mouse"),
                new Product(4, "Keyboard"),
                new Product(5, "Webcam")
        );
    }
}
