package com.ogawalucas.class04practice04.ejb;

import com.ogawalucas.class04pracitce04.service.ProductService;
import com.ogawalucas.class04practice04.model.Product;
import java.util.List;
import javax.ejb.Stateless;

@Stateless
public class ProductEjb {

    public List<Product> getAll() {
        return new ProductService().getAll();
    }
}
