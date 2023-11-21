import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientReadThread extends Thread{
  private final BufferedReader fromServer;

  public ClientReadThread(Socket socket) {
    try {
      this.fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

  }

  @Override
  public void run() {
    while (true) {
      try {
        String line = this.fromServer.readLine();

        if (line == null) {
          System.err.println("Connection closed.");
          break;
        }

        System.out.println(line);
      } catch (IOException e) {
        System.err.println("Error reading from server");
        e.printStackTrace();
        break;
      }
    }
  }

}
