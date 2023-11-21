import javax.sound.sampled.Port;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Server {
  public static final int PORT = 12345;
  public static void main(String[] args) {
    Server server = new Server(PORT);

    server.execute();
  }

  private int port;
  private final Set<UserThread> users;

  public Server(int port) {
    this.port = port;
    this.users = new HashSet<>(); // TODO ?
  }

  private void execute() {
    try (ServerSocket server = new ServerSocket(this.port)) {
      System.err.println("Chat server is listening on port: " + this.port);

      while (true) {
        Socket client = server.accept();
        try {
          UserThread user = new UserThread(client, this);
          this.users.add(user);
          user.start();
        } catch (IOException e) {
          // ignored
          // TODO
        }
      }

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  synchronized List<String> getUserNames() {
    return this.users.stream().map(UserThread::getUsername).collect(Collectors.toList());
  }

  synchronized void broadcast(UserThread sender, String message) {
    this.users.stream().filter(u -> u != sender).forEach(u -> u.sendToClient(message));
  }

  void remove(UserThread user) {
    this.users.remove(user);
    System.err.println("Removed client: " + user.getUsername() + " | " + user.getId());
  }
}
