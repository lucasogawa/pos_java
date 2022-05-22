package com.ogawalucas.class04practice04activity.jsf;

import com.ogawalucas.class04practice04activity.ejb.CalculatorEjb;
import com.ogawalucas.class04practice04activity.ejb.ScoreboardEjb;
import com.ogawalucas.class04practice04activity.model.Scoreboard;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import lombok.Getter;
import lombok.Setter;

@Named(value = "calculatorJsf")
@SessionScoped
public class CalculatorJsf implements Serializable {

    @EJB
    private CalculatorEjb ejb;
    
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private int valueA;
    @Getter
    @Setter
    private int valueB;
    @Getter
    @Setter
    private int result;
    @Getter
    @Setter
    private String resultMessage;
    
    public CalculatorJsf() {
        this.resultMessage = "-";
    }
    
    public void saveName() {
        ejb.setName(name);
        generateValues();
    }
    
    private void generateValues() {
        ejb.generateValues();
        this.valueA = ejb.getValueA();
        this.valueB = ejb.getValueB();
    }
    
    public void verify() {
        this.resultMessage = ejb.verify(result);
        updateScoreboard();
        generateValues();
        clearResult();
    }
    
    private void clearResult() {
        this.result = 0;
    }
    
    private void updateScoreboard() {
        if (this.resultMessage.contains("RIGHT")) {
            Scoreboard.add(this.name);
        }
    }
}
