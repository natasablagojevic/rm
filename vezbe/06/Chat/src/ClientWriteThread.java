import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientWriteThread extends Thread {
  private final PrintWriter toServer;
  private final String name;
  public ClientWriteThread(Socket socket, String name) throws IOException {
    this.toServer = new PrintWriter(socket.getOutputStream(), true);
    this.name = name;
  }

  @Override
  public void run() {
    this.toServer.println(this.name);
    try (Scanner sc = new Scanner(System.in)) {
      String msg;
      do {
        msg = sc.nextLine
                ();
        this.toServer.println(msg);
      } while (msg.equalsIgnoreCase("bye"));
    }
  }
}
