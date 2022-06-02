package com.ogawalucas.class04practice06activity.jsf;

import com.ogawalucas.class04practice06activity.ejb.CalculatorEjb;
import com.ogawalucas.class04practice06activity.model.Scoreboard;
import com.ogawalucas.class04practice06activity.model.ScoreboardItem;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.ObjectMessage;
import javax.jms.Queue;

@Named(value = "calculatorJsf")
@SessionScoped
public class CalculatorJsf implements Serializable {

    @EJB
    private CalculatorEjb ejb;
    
    @Resource(lookup = "java:comp/DefaultJMSConnectionFactory")
    private ConnectionFactory connectionFactory;
    @Resource(lookup = "queue/ranking")
    private Queue queue;
    
    private String name;
    private int valueA;
    private int valueB;
    private int result;
    private String resultMessage;
    
    public CalculatorJsf() {
        this.resultMessage = "-";
    }

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

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
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
            Scoreboard.scoreboardItems.stream()
                .filter(scoreboardItem -> scoreboardItem.getName().equals(name))
                .findFirst()
                .ifPresentOrElse(
                        scoreboardItem -> scoreboardItem.setPoints(scoreboardItem.getPoints() + 1),
                        () -> {
                            Scoreboard.scoreboardItems.add(new ScoreboardItem(name, 1));
                            sendMessage();
                        }
                );
        }
    }
    
    private void sendMessage() {
        try {
            JMSContext context = connectionFactory.createContext();
            ObjectMessage obj = context.createObjectMessage();
            
            obj.setObject(new ArrayList<ScoreboardItem>(Scoreboard.scoreboardItems));
            context.createProducer().send(queue, obj);
        } catch(Exception ex) {
            System.out.println("Error during send message to Queue.");
            System.out.println(ex);
        }
    }
}
