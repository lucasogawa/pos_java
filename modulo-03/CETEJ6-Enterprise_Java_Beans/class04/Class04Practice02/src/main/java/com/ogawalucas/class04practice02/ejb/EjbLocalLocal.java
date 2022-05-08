/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package com.ogawalucas.class04practice02.ejb;

import javax.ejb.Local;

/**
 *
 * @author default
 */
@Local
public interface EjbLocalLocal {
    
    public int doubleValue(int value);
}
