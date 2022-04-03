import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LeituraUtils {

    public static String lerString(String mensagem) throws IOException {
        System.out.println(mensagem);
        return new BufferedReader(new InputStreamReader(System.in)).readLine();
    }
}
