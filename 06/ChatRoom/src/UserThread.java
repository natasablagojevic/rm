import java.io.*;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;

public class UserThread extends Thread{
  ChatServer server;
  Socket socket;
  BufferedReader fromUser;
  PrintWriter toUser;
  String username;

  public UserThread(Socket socket, ChatServer chatServer) {
    this.server = chatServer;
    this.socket = socket;

    try {
      this.fromUser = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      this.toUser = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    this.username = null;
  }

  @Override
  public void run() {
    try {
      this.username = this.fromUser.readLine();
      System.out.println(Arrays.toString(new List[]{this.server.getUsernames()}));
      this.server.broadcast(this, "Usr " + this.username + " is online.");

      while (true) {
        String lineRead = this.fromUser.readLine();
        if (lineRead == null || lineRead.equalsIgnoreCase("bye")) {
          this.server.broadcast(this, "User " + this.username + " has left the chat.");
          break;
        }
        this.server.broadcast(this, lineRead);
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (this.socket != null) {
        this.socket.remove();
        try {
          socket.close();
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
      }
    }
  }

  String getUsername() {
    return this.username;
  }

  void sendMessage(String msg) {
    this.toUser.println(msg);
  }
}
