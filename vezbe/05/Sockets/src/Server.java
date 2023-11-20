import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class Server {

  public static final int PORT = 12345;

  public static void main(String[] args) {
    try (ServerSocket server = new ServerSocket(PORT)) {

      System.err.println("Server bound to port: " + PORT);

      while (true) {
        try (Socket client = server.accept()) {

          BufferedWriter out = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
          String now = new Date().toString();
          out.write(now);
          out.newLine();
          out.flush();
        }
      }

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
