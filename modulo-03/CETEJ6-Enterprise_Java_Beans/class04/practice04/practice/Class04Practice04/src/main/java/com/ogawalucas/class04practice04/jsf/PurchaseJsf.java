package com.ogawalucas.class04practice04.jsf;

import com.ogawalucas.class04practice04.ejb.PurchaseEjb;
import com.ogawalucas.class04practice04.model.Product;
import com.ogawalucas.class04practice04.model.PurchaseItem;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;

@Named(value = "purchaseJsf")
@SessionScoped
public class PurchaseJsf implements Serializable {

    @EJB
    private PurchaseEjb ejb;
    
    public PurchaseJsf() {
        
    }
    
    public void add(Product product) {
        ejb.add(product);
    }
    
    public List<PurchaseItem> getAll() {
        return ejb.getAll();
    }
    
    public void clear() {
        ejb.clear();
    }
}
