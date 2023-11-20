import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientReadThread extends Thread{
  BufferedReader fromServer;
  String username;

  public ClientReadThread(String username, Socket socket) {
    this.username = username;
    try {
      this.fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void run() {
    while (true) {
      try {
        // Wait for message and print it
        String response = this.fromServer.readLine();
        if (response == null) {
          System.err.println("\rConnection lost.");
          return;
        }
        System.out.println("\r" + response);

        // Print prompt
        System.out.printf("\r[%s]: ", this.username);
      } catch (IOException ex) {
        System.out.println("Error reading from server: " + ex.getMessage());
        ex.printStackTrace();
        break;
      }
    }
  }
}
