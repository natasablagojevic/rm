import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
  public final static int DEFAULT_PORT = 8765;

  public static void main(String[] args) {
    try (ServerSocket socket = new ServerSocket(DEFAULT_PORT)) {

      while (true) {
        System.out.println("Listening on port " + DEFAULT_PORT);
        Socket client = socket.accept();
        System.out.println("Client accepted " + client.getLocalPort());
        new Thread(new ClientHandlerRunnable(client)).start();

      }


//      socket.close();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
