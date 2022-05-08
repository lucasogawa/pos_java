package com.ogawalucas.class04practice02.ejb;

import javax.ejb.Stateless;

@Stateless
public class EjbLocal implements EjbLocalLocal {
    
    public int doubleValue(int value) {
        return value * 2;
    }
}
