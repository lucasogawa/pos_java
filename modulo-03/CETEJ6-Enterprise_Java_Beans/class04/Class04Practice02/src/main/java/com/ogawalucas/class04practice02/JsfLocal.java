package com.ogawalucas.class04practice02;

import com.ogawalucas.class04practice02.ejb.EjbLocalLocal;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

@Named(value = "jsfLocal")
@RequestScoped
public class JsfLocal {

    @EJB
    private EjbLocalLocal ejbLocal;

    private int value;
    private int result;

    public JsfLocal() {

    }
    
    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }
    
    public void doubleValue() {
        result = ejbLocal.doubleValue(value);
    }
}
