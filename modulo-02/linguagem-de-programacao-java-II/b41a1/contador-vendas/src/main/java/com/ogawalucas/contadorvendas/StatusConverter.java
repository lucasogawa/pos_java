package com.ogawalucas.contadorvendas;

import com.opencsv.bean.AbstractBeanField;

public class StatusConverter extends AbstractBeanField<String, Sale.Status> {

    @Override
    protected Sale.Status convert(String value) {
        return Sale.Status.statusByValue(value);
    }
}
