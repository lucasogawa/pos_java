package com.ogawalucas.class05practice03gui.ejb;

import com.ogawalucas.class05practice03lib.interfaces.ICalculator;
import java.util.Properties;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class EjbClient {
    
    public int sum(int a, int b) {
        
        var props = new Properties();
        props.setProperty("java.naming.factory.initial", "com.sun.enterprise.naming.SerialInitContextFactory");
        props.setProperty("java.naming.factory.url.pkgs","com.sun.enterprise.naming");
        props.setProperty("java.naming.factory.state","com.sun.corba.ee.impl.presentation.rmi.JNDIStateFactoryImpl");
        props.setProperty("org.omg.CORBA.ORBInitialHost", "localhost");
        props.setProperty("org.omg.CORBA.ORBInitialPort", "3700");
        props.setProperty("com.sun.corba.ee.transport.ORBWaitForResponseTimeout","5000");
        props.setProperty("com.sun.corba.ee.transport.ORBTCPConnectTimeouts","100:500:100:500");
        props.setProperty("com.sun.corba.ee.transport.ORBTCPTimeouts","500:2000:50:1000");

        InitialContext ejbRemoteContext;
        try {
            ejbRemoteContext = new InitialContext(props);
        } catch (NamingException ex) {
            System.out.println("ERRO");
            System.out.println(ex.getMessage());
            return -1;
        }

        ICalculator beanRemote;
        try {
            beanRemote = (ICalculator) ejbRemoteContext.lookup("java:global/class05Practice03Web/EjbCalculator");
            int x = beanRemote.sum(a, b);
            return x;
        } catch (NamingException ex) {
            System.out.println("ERRO");
            System.out.println(ex.getMessage());
            return -2;
        }
    }
}
