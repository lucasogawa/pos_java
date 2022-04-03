import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(2000);
            Socket socket = serverSocket.accept();
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());

            dataOutputStream.writeUTF(String.valueOf(CpfUtils.isValido(dataInputStream.readUTF())).toUpperCase());

            socket.close();
        } catch (Exception ex) {
            System.out.println("Erro.");
        }
    }
}

