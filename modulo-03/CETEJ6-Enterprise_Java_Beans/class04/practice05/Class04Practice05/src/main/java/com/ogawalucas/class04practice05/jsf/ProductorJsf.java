package com.ogawalucas.class04practice05.jsf;

import javax.annotation.Resource;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.jms.ConnectionFactory;
import javax.jms.Queue;
import lombok.Getter;
import lombok.Setter;

@Named(value = "productorJsf")
@RequestScoped
public class ProductorJsf {

    @Resource(lookup = "java:comp/DefaultJMSConnectionFactory")
    private ConnectionFactory connectionFactory;
    @Resource(lookup = "java/Queue")
    private Queue queue;
    
    @Getter
    @Setter
    private String message;
    
    public ProductorJsf() {
        
    }
    
    public void send() {
        try {
            connectionFactory.createContext().createProducer().send(queue, message);   
        } catch (Exception ex) {
            System.out.println("Error during send message.");
        }
    }
}
