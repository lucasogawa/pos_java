import java.rmi.registry.LocateRegistry;

public class Client {

    public Client() {
        try {
            var register = LocateRegistry.getRegistry("localhost", 1099);
            var object = (VoteCounter) register.lookup("voteCounterMethod");

            System.out.println("Votes has been saved: " + object.countVote("Lucas", 5));
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Client();
    }
}

