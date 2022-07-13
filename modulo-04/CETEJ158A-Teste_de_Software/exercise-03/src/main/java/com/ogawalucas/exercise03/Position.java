package com.ogawalucas.exercise03;

import java.math.BigDecimal;

public enum Position {

    DEVELOPER() {
        @Override
        BigDecimal getLiquidSalary(BigDecimal baseSalary) {
            return baseSalary.multiply(BigDecimal.ONE.subtract(getDiscountPercent(baseSalary))).setScale(2);
        }

        @Override
        BigDecimal getDiscountPercent(BigDecimal baseSalary) {
            return baseSalary.compareTo(BigDecimal.valueOf(3000)) >= 0
                ? BigDecimal.valueOf(0.20)
                : BigDecimal.valueOf(0.10);
        }
    },

    DBA() {
        @Override
        BigDecimal getLiquidSalary(BigDecimal baseSalary) {
            return baseSalary.multiply(BigDecimal.ONE.subtract(getDiscountPercent(baseSalary))).setScale(2);
        }

        @Override
        BigDecimal getDiscountPercent(BigDecimal baseSalary) {
            return baseSalary.compareTo(BigDecimal.valueOf(2000)) >= 0
                ? BigDecimal.valueOf(0.25)
                : BigDecimal.valueOf(0.15);
        }
    },

    TESTER() {
        @Override
        BigDecimal getLiquidSalary(BigDecimal baseSalary) {
            return baseSalary.multiply(BigDecimal.ONE.subtract(getDiscountPercent(baseSalary))).setScale(2);
        }

        @Override
        BigDecimal getDiscountPercent(BigDecimal baseSalary) {
            return baseSalary.compareTo(BigDecimal.valueOf(2000)) >= 0
                ? BigDecimal.valueOf(0.25)
                : BigDecimal.valueOf(0.15);
        }
    },

    MANAGER() {
        @Override
        BigDecimal getLiquidSalary(BigDecimal baseSalary) {
            return baseSalary.multiply(BigDecimal.ONE.subtract(getDiscountPercent(baseSalary))).setScale(2);
        }

        @Override
        BigDecimal getDiscountPercent(BigDecimal baseSalary) {
            return baseSalary.compareTo(BigDecimal.valueOf(5000)) >= 0
                ? BigDecimal.valueOf(0.30)
                : BigDecimal.valueOf(0.20);
        }
    };

    abstract BigDecimal getLiquidSalary(BigDecimal baseSalary);

    abstract BigDecimal getDiscountPercent(BigDecimal baseSalary);
}

