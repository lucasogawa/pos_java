package com.ogawalucas.class04practice03activity.jsf;

import com.ogawalucas.class04practice03activity.ejb.EjbCalculator;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

@Named(value = "jsfCalculator")
@RequestScoped
public class JsfCalculator {

    @EJB
    private EjbCalculator ejbCalculator;
    
    private int valueA;
    private int valueB;
    private int result;
    
    public JsfCalculator() {
        
    }

    public int getValueA() {
        return valueA;
    }

    public void setValueA(int valueA) {
        this.valueA = valueA;
    }

    public int getValueB() {
        return valueB;
    }

    public void setValueB(int valueB) {
        this.valueB = valueB;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }
    
    public void sum() {
        result = ejbCalculator.sum(valueA, valueB);
    }
}
