package com.ogawalucas.class04practice06activity.ejb;

import com.ogawalucas.class04practice06activity.model.ScoreboardItem;
import java.util.ArrayList;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue="queue/ranking"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue="javax.jms.Queue")
})
public class RankingConsumerQueueEJb implements MessageListener {

    @Override
    public void onMessage(Message msg) {
        try {
            ((ArrayList<ScoreboardItem>)((ObjectMessage) msg).getObject())
                    .forEach(scoreboardItem -> System.out.println(scoreboardItem.getName() + " - " + scoreboardItem.getPoints()));
        } catch(Exception ex) {
            System.out.println("Error during recive message.");
            System.out.println(ex);
        }
    }
}
