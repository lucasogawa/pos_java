package com.ogawalucas.exercise03;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class EmployeeTest {

    @Test
    public void mustDiscount10Percent_whenTheEmployeeIsDeveloperAndHasSalaryLessThan3000() {
        assertEquals(
            BigDecimal.valueOf(900).setScale(2),
            oneDeveloper(BigDecimal.valueOf(1000)).getLiquidSalary()
        );
    }

    @Test
    public void mustDiscount20Percent_whenTheEmployeeIsDeveloperAndHasSalaryEqualTo3000() {
        assertEquals(
            BigDecimal.valueOf(2400).setScale(2),
            oneDeveloper(BigDecimal.valueOf(3000)).getLiquidSalary()
        );
    }

    @Test
    public void mustDiscount20Percent_whenTheEmployeeIsDeveloperAndHasSalaryMoreThan3000() {
        assertEquals(
            BigDecimal.valueOf(3200).setScale(2),
            oneDeveloper(BigDecimal.valueOf(4000)).getLiquidSalary()
        );
    }

    private Employee oneDeveloper(BigDecimal baseSalary) {
        return new Employee("Developer", "developer@email.com", baseSalary, Position.DEVELOPER);
    }

    @Test
    public void mustDiscount15Percent_whenTheEmployeeIsDbaAndHasSalaryLessThan2000() {
        assertEquals(
            BigDecimal.valueOf(850).setScale(2),
            oneDba(BigDecimal.valueOf(1000)).getLiquidSalary()
        );
    }

    @Test
    public void mustDiscount25Percent_whenTheEmployeeIsDbaAndHasSalaryEqualTo2000() {
        assertEquals(
            BigDecimal.valueOf(1500).setScale(2),
            oneDba(BigDecimal.valueOf(2000)).getLiquidSalary()
        );
    }

    @Test
    public void mustDiscount25Percent_whenTheEmployeeIsDbaAndHasSalaryMoreThan2000() {
        assertEquals(
            BigDecimal.valueOf(2250).setScale(2),
            oneDba(BigDecimal.valueOf(3000)).getLiquidSalary()
        );
    }

    private Employee oneDba(BigDecimal baseSalary) {
        return new Employee("Dba", "dba@email.com", baseSalary, Position.DBA);
    }

    @Test
    public void mustDiscount15Percent_whenTheEmployeeIsTesterAndHasSalaryLessThan2000() {
        assertEquals(
            BigDecimal.valueOf(850).setScale(2),
            oneTester(BigDecimal.valueOf(1000)).getLiquidSalary()
        );
    }

    @Test
    public void mustDiscount25Percent_whenTheEmployeeIsTesterAndHasSalaryEqualTo2000() {
        assertEquals(
            BigDecimal.valueOf(1500).setScale(2),
            oneTester(BigDecimal.valueOf(2000)).getLiquidSalary()
        );
    }

    @Test
    public void mustDiscount25Percent_whenTheEmployeeIsTesterAndHasSalaryMoreThan2000() {
        assertEquals(
            BigDecimal.valueOf(2250).setScale(2),
            oneTester(BigDecimal.valueOf(3000)).getLiquidSalary()
        );
    }

    private Employee oneTester(BigDecimal baseSalary) {
        return new Employee("Tester", "tester@email.com", baseSalary, Position.TESTER);
    }

    @Test
    public void mustDiscount20Percent_whenTheEmployeeIsManagerAndHasSalaryLessThan5000() {
        assertEquals(
            BigDecimal.valueOf(3200).setScale(2),
            oneManager(BigDecimal.valueOf(4000)).getLiquidSalary()
        );
    }

    @Test
    public void mustDiscount30Percent_whenTheEmployeeIsManagerAndHasSalaryEqualTo5000() {
        assertEquals(
            BigDecimal.valueOf(3500).setScale(2),
            oneManager(BigDecimal.valueOf(5000)).getLiquidSalary()
        );
    }

    @Test
    public void mustDiscount30Percent_whenTheEmployeeIsManagerAndHasSalaryMoreThan5000() {
        assertEquals(
            BigDecimal.valueOf(4200).setScale(2),
            oneManager(BigDecimal.valueOf(6000)).getLiquidSalary()
        );
    }

    private Employee oneManager(BigDecimal baseSalary) {
        return new Employee("Manager", "manager@email.com", baseSalary, Position.MANAGER);
    }
}

