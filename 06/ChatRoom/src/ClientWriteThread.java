import java.io.*;
import java.net.Socket;

public class ClientWriteThread extends Thread{
  String username;
  PrintWriter toServer;
  ClientWriteThread(String username, Socket socket) {
    this.username = username;
    try {
      this.toServer = new PrintWriter(socket.getOutputStream(), true);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void run() {
    while (true) {
      BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

      try {
        String lineRead = in.readLine();
        this.toServer.println(lineRead);

      } catch (IOException e) {
        // TODO obraditi ....
        e.printStackTrace();
      }
    }
  }
}
