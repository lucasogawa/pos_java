package com.ogawalucas.class04practice05.ejb;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;


@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName="destinationLookup", 
            propertyValue="java/Queue"),
    @ActivationConfigProperty(propertyName="destinationType", 
            propertyValue="javax.jms.Queue")
})
public class ConsumerEjb implements MessageListener {
    
    @Override
    public void onMessage(Message msg) {
        try {
            System.out.println("Message recived: " + ((TextMessage)msg).getText());
        } catch(Exception ex) {
            System.out.println("Error during recive message.");
        }
    }
}
