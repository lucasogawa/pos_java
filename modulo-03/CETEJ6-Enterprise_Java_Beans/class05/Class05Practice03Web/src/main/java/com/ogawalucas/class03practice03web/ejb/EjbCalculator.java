package com.ogawalucas.class03practice03web.ejb;

import com.ogawalucas.class05practice03lib.interfaces.ICalculator;
import javax.ejb.Stateless;

@Stateless
public class EjbCalculator implements ICalculator {

    @Override
    public int sum(int a, int b) {
        return a + b;
    }    
}
