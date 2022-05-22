package com.ogawalucas.class04practice04activity.ejb;

import java.util.Random;
import javax.ejb.Stateful;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Stateful
public class CalculatorEjb {

    private String name;
    private int valueA;
    private int valueB;
    private String resultMessage;
    
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
