import java.rmi.Remote;
import java.rmi.RemoteException;

public interface VoteCounter extends Remote {

    boolean countVote(String candidateName, int numberVotes) throws RemoteException;
}

