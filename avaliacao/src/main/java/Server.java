import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

public class Server implements Calculadora {

    public static void main(String[] args) {
        new Server();
    }

    public Server() {
        try {
            var object = (Calculadora) UnicastRemoteObject.exportObject(this, 0);
            var register = LocateRegistry.getRegistry("localhost", 1099);

            register.bind("calculadoraMethods", object);

            System.out.println("Server is ready!\n");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public long add(long a, long b) throws RemoteException {
        return a + b;
    }

    @Override
    public long sub(long a, long b) throws RemoteException {
        return a - b;
    }

    @Override
    public long mul(long a, long b) throws RemoteException {
        return a * b;
    }

    @Override
    public long div(long a, long b) throws RemoteException {
        return a / b;
    }
}