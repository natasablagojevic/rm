import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ServerSocketIntro {
    public static void main(String[] args) {
      String host = "alas.matf.bg.ac.rs";

      for (int i = 1; i < 65536; i++) {
        try {
          System.out.printf("Skeniramo: %5d\n", i);
          Socket client = new Socket(host, i);
          System.out.println("\rPort: " + i + " je otvoren...           ");

          client.close();
        } catch (UnknownHostException e) {
          System.err.println("Ne postoji host!");
          e.printStackTrace();
          break;
        } catch (IOException e) {
          // nije otvoren
          //throw new RuntimeException(e);
        }

      }
    }
}
