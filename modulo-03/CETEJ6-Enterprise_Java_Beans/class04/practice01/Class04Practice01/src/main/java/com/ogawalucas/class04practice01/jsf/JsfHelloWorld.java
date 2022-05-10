package com.ogawalucas.class04practice01.jsf;

import com.ogawalucas.class04practice01.ejb.EjbHelloWorld;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

@Named(value = "jsfHelloWorld")
@RequestScoped
public class JsfHelloWorld {

    @EJB
    private EjbHelloWorld ejbHelloWorld;

    public JsfHelloWorld() {
    
    }
    
    public String getHelloWorld() {
        return ejbHelloWorld.getHelloWorld();
    }
}
