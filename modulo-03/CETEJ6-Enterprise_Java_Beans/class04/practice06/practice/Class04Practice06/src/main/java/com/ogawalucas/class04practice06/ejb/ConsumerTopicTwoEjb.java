package com.ogawalucas.class04practice06.ejb;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@MessageDriven(activationConfig={
    @ActivationConfigProperty(propertyName="destinationLookup", propertyValue="java/Topic"),
    @ActivationConfigProperty(propertyName="destinationType", propertyValue="javax.jms.Topic")
})
public class ConsumerTopicTwoEjb implements MessageListener {

    @Override
    public void onMessage(Message msg) {
        try {
            System.out.println("[" + this.getClass().getSimpleName() + "] " + "Message recive from Topic: " + ((TextMessage)msg).getText());
        } catch(Exception ex) {
            System.out.println("Error during recive message from Topic.");
        }
    }
}
