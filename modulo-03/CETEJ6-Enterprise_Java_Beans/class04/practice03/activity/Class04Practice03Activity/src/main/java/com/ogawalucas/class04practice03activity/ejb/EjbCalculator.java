package com.ogawalucas.class04practice03activity.ejb;

import javax.ejb.Stateless;

@Stateless
public class EjbCalculator {
    
    public int sum(int a, int b) {
        return a + b;
    }
} 
