import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
  public static final int PORT = 12346;
  public static void main(String[] args) {
    try (ServerSocket server = new ServerSocket(PORT)) {
      System.err.println("Server bound to port: " + PORT);

      while (true) {
        System.err.println("Listening for clients...");
        Socket client = server.accept();
        System.err.println("Accepted client!");

        /**
         * JOIN radimo kada nam je bitno u trenutnom Thread-u iz kojeg radimo JOIN
         * bitan nam je rezultat rada Thread-a
         */
        new Thread(new ClientHandlerRunnable(client)).start();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
