package com.ogawalucas.class04practice01.ejb;

import javax.ejb.Stateless;

@Stateless
public class EjbHelloWorld {

    public String getHelloWorld() {
        return "Hello World!";
    }
}
