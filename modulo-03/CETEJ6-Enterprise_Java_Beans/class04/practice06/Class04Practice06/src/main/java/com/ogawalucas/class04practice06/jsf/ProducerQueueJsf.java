package com.ogawalucas.class04practice06.jsf;

import java.util.stream.IntStream;
import javax.annotation.Resource;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;
import lombok.Getter;
import lombok.Setter;

@Named(value = "producerQueueJsf")
@RequestScoped
public class ProducerQueueJsf {

    @Resource(lookup = "java:comp/DefaultJMSConnectionFactory")
    private ConnectionFactory connectionFactory;
    @Resource(lookup = "java/Queue")
    private Queue queue;
    
    @Getter @Setter
    private String message;
    @Getter @Setter
    private int quantity = 1;
    
    public ProducerQueueJsf() {
    
    }
    
    public void send() {
        try {
            JMSContext context = connectionFactory.createContext();
            
            IntStream.range(0, this.quantity)
                    .forEachOrdered(index -> context.createProducer().send(queue, "[" + index + "]: " + message));
        } catch(Exception ex) {
            System.out.println("Error during send message to Queue.");
        }
    }
}
