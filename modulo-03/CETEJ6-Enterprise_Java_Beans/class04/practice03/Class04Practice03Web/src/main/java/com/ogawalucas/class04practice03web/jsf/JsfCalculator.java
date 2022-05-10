package com.ogawalucas.class04practice03web.jsf;

import com.ogawalucas.class04practice03lib.interfaces.ICalculator;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import lombok.Getter;
import lombok.Setter;

@Named(value = "jsfCalculator")
@RequestScoped
public class JsfCalculator {

    @EJB
    private ICalculator ejbCalculator;
    
    @Getter @Setter
    private int valueA;
    @Getter @Setter
    private int valueB;
    @Getter @Setter
    private int result;
    
    public JsfCalculator() {
        
    }
    
    public void sum() {
        result = ejbCalculator.sum(valueA, valueB);
    }
}
