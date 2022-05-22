package com.ogawalucas.class04practice04.jsf;

import com.ogawalucas.class04practice04.ejb.ProductEjb;
import com.ogawalucas.class04practice04.model.Product;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

@Named(value = "productJsf")
@RequestScoped
public class ProductJsf {

    @EJB
    private ProductEjb ejb;
    
    public ProductJsf() {
        
    }
    
    public List<Product> getAll() {
        return ejb.getAll();
    }
}
