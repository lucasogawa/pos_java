package com.ogawalucas.class04practice04.ejb;

import com.ogawalucas.class04practice04.model.Product;
import com.ogawalucas.class04practice04.model.PurchaseItem;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateful;

@Stateful
public class PurchaseEjb {

    private List<PurchaseItem> purchaseItems;
    
    public PurchaseEjb() {
        purchaseItems = new ArrayList<>();
    }
    
    public void add(Product product) {
        purchaseItems.stream()
                .filter(purchaseItem -> purchaseItem.getProduct().getCode() == product.getCode())
                .findFirst()
                .ifPresentOrElse(
                        purchaseItem -> purchaseItem.setQuantity(purchaseItem.getQuantity() + 1), 
                        () -> purchaseItems.add(new PurchaseItem(product, 1))
                );
    }
    
    public List<PurchaseItem> getAll() {
        return purchaseItems;
    }
    
    public void clear() {
        purchaseItems = new ArrayList<>();
    }
}
