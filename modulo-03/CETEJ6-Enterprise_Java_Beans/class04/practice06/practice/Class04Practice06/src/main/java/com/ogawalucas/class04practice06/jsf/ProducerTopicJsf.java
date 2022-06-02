package com.ogawalucas.class04practice06.jsf;

import java.util.stream.IntStream;
import javax.annotation.Resource;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Topic;
import lombok.Getter;
import lombok.Setter;

@Named(value = "producerTopicJsf")
@RequestScoped
public class ProducerTopicJsf {

    @Resource(lookup = "java:comp/DefaultJMSConnectionFactory")
    private ConnectionFactory connectionFactory;
    @Resource(lookup = "java/Topic")
    private Topic topic;
    
    @Getter @Setter
    private String message;
    @Getter @Setter
    private int quantity = 1;
    
    public ProducerTopicJsf() {
    
    }
    
    public void send() {
        try {
            JMSContext context = connectionFactory.createContext();
            
            IntStream.range(0, this.quantity)
                    .forEachOrdered(index -> context.createProducer().send(topic, "[" + index + "]: " + message));
        } catch(Exception ex) {
            System.out.println("Error during send message to Queue.");
        }
    }
}
