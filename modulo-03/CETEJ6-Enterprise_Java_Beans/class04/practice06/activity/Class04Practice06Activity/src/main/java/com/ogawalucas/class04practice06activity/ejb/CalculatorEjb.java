package com.ogawalucas.class04practice06activity.ejb;

import java.util.Random;
import javax.ejb.Stateful;

@Stateful
public class CalculatorEjb {

    private String name;
    private int valueA;
    private int valueB;
    private String resultMessage;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValueA() {
        return valueA;
    }

    public void setValueA(int valueA) {
        this.valueA = valueA;
    }

    public int getValueB() {
        return valueB;
    }

    public void setValueB(int valueB) {
        this.valueB = valueB;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }
    
    public void generateValues() {
        Random random = new Random();
        
        this.valueA = random.nextInt(10);
        this.valueB = random.nextInt(10);
    }
    
    public String verify(int result) {
        if (this.valueA + this.valueB == result) {
            this.resultMessage = getResultMessageFormatted("RIGHT", result);
        } else {
            this.resultMessage = getResultMessageFormatted("WRONG", result);
        }
        
        return this.resultMessage;
    }
    
    private String getResultMessageFormatted(String resultMessage, int result) {
        return String.format("%s => [%s] + [%s] = [%s]", resultMessage, this.valueA, this.valueB, result);
    }
}
