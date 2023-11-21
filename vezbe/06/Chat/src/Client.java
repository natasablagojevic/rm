import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
  public static void main(String[] args) {
    String hostanme = "localhost";
    int port = 12345;
    Client client = new Client(hostanme, port);

    System.err.println("Connecting to: " + hostanme + " : " + port);

    client.execute();
  }

  private final String hostname;
  private final int port;
  private  String name;

  Client (String hostname, int port) {
    this.hostname = hostname;
    this.port = port;
  }

  void execute() {
    this.setName();

    try (Socket socket = new Socket(this.hostname, this.port)) {
      System.err.println("Connected to chat server @ " + this.hostname);

      Thread rt = new ClientReadThread(socket);
      Thread wt = new ClientWriteThread(socket, this.name);

      rt.start();
      wt.start();

      rt.join();
      wt.join();

    } catch (UnknownHostException e) {
      System.err.println("Server not found: " + this.hostname);
    } catch (IOException e) {
      e.printStackTrace();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  private void setName() {
    try (Scanner sc = new Scanner(System.in)) {
      System.out.println("Enter your name:");
      this.name = sc.next();
    }
  }
}
