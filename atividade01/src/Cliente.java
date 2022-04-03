import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class Cliente {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1", 2000);
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());

            dataOutputStream.writeUTF(LeituraUtils.lerString("CPF: "));
            System.out.println(dataInputStream.readUTF());

            socket.close();
        } catch (Exception ex) {

        }
    }
}

