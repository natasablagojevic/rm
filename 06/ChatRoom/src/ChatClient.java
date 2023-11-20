import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class ChatClient {
  public static void main(String[] args) {
    new ChatClient("localhost", ChatServer.S    zzERVER_TEST_PORT);
    System.out.println("Client connected to..." + ChatServer.SERVER_TEST_PORT);


  }

  final String hostname;
  final int port;
  String username;

  ChatClient(String hostname, int port) {

    this.hostname = hostname;
    this.port = port;

    try {
      BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
      in.readLine();

    } catch (IOException e) {
      System.err.println("Unabled to create client!");
      e.printStackTrace();
    }

    execute();
  }

  void execute() {

    try (Socket client = new Socket(this.hostname, this.port)) {

      ClientReadThread readThread = new ClientReadThread(this.username, client);
      ClientWriteThread writeThread = new ClientWriteThread(this.username, client);

      readThread.start();
      writeThread.start();

      readThread.join();
      writeThread.join();




    } catch (UnknownHostException e) {
      // TODO obrada gresaka
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }

//    ClientReadThread readThread = new ClientReadThread();

  }
}
