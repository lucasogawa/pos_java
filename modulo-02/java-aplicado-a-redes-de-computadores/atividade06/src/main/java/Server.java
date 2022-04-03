import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class Server implements VoteCounter {

    private final Map<String, Integer> candidateVotes = new HashMap<>();

    public Server() {
        try {
            var object = (VoteCounter) UnicastRemoteObject.exportObject(this, 0);
            var register = LocateRegistry.getRegistry("localhost", 1099);

            register.bind("voteCounterMethod", object);

            System.out.println("Server is ready!\n");

            while (true) {
                showVotes();
                Thread.sleep(5000);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public boolean countVote(String candidateName, int numberVotes) {
        try {
            if (candidateVotes.containsKey(candidateName)) {
                candidateVotes.put(candidateName, candidateVotes.get(candidateName) + numberVotes);
            } else {
                candidateVotes.put(candidateName, numberVotes);
            }
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    private void showVotes() {
        System.out.printf("========== %s ==========",
            LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"))).println();

        if (candidateVotes.isEmpty()) {
            System.out.println("\tNo votes saved yet");
        } else {
            candidateVotes.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .forEach(candidateVotes ->
                    System.out.println("\t" + candidateVotes.getKey() + ": " + candidateVotes.getValue()));
        }

        System.out.println();
    }

    public static void main(String[] args) {
        new Server();
    }
}