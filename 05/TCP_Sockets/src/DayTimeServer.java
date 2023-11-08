import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class DayTimeServer {
  public final static int DEFAULT_PORT = 8765;
  public static void main(String[] args) {

    try (ServerSocket socket = new ServerSocket(DEFAULT_PORT)) {

      // da ne odradimo smo za jednog klijenta, vec da opsluzujemo dosta njih ..
      while (true) {
        System.err.println("Listening on port " + DEFAULT_PORT);
        Socket client;
        try {
          client = socket.accept();
        } catch (IOException e) {
          System.err.println("Error while listening on port " + DEFAULT_PORT);
          continue;
        }

        System.err.println("Client connected " + client.getLocalPort());
        var out = new BufferedOutputStream(client.getOutputStream());

        String curr = new Date().toString();
        byte []bytes = curr.getBytes();
        out.write(bytes);
        out.flush();
        client.close();
        System.err.println("Current time: " + curr);
      }
//      System.out.println(new Date());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}

