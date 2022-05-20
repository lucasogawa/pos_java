package com.ogawalucas.class04practice03lib.interfaces;

import javax.ejb.Remote;

@Remote
public interface ICalculator {
    
    public int sum(int a, int b);
}
