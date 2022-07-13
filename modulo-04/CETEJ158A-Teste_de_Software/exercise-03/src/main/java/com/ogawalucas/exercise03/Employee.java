package com.ogawalucas.exercise03;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    private String name;
    private String email;
    private BigDecimal baseSalary;
    private Position position;

    public BigDecimal getLiquidSalary() {
        return this.position.getLiquidSalary(this.baseSalary);
    }
}

