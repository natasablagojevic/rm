import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

public class UserThread extends Thread {
  private final Socket client;
  private final Server server;
  private String username;
  private final BufferedReader fromUser;
  private final PrintWriter toUser;

  public UserThread(Socket client, Server server) throws IOException {
    this.client = client;
    this.server = server;
    this.fromUser = new BufferedReader(new InputStreamReader(this.client.getInputStream()));
    this.toUser = new PrintWriter(this.client.getOutputStream(), true);
  }

  @Override
  public void run() {
    try {
      this.username = this.fromUser.readLine();

      this.sendToClient("Connected users: " + this.server.getUserNames());

      this.server.broadcast(this,"New user connected: " + this.username);

      String msg;

      do {
        msg = this.fromUser.readLine();

        if (msg == null) {
          break;
        }

        this.server.broadcast(this,"[" + this.username + "]: " + msg);
      } while (!msg.equalsIgnoreCase("bye"));

      this.server.broadcast(this, this.username + " has left the chat.");

    } catch (IOException e) {
      System.err.println("Error in UserThread");
      e.printStackTrace();
    } finally {
      this.server.remove(this);
      try {
        this.client.close();
      } catch (IOException e) {
        // ignored
      }
    }

  }

  public void sendToClient(String msg) {
    this.toUser.println(msg);
  }

  public String getUsername() {
    return username;
  }
}
