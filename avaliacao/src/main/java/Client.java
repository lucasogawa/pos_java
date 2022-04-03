import java.rmi.registry.LocateRegistry;

public class Client {

    public static void main(String[] args) {
        new Client();
    }

    public Client() {
        try {
            var register = LocateRegistry.getRegistry("localhost", 1099);
            var object = (Calculadora) register.lookup("calculadoraMethods");

            System.out.println("1 + 1 = " + object.add(1, 1));
            System.out.println("2 - 2 = " + object.sub(2, 2));
            System.out.println("3 * 3 = " + object.mul(3, 3));
            System.out.println("4 / 4 = " + object.div(4, 4));
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}

