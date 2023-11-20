import java.io.BufferedReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ChatServer {
  static final int SERVER_TEST_PORT = 12345;

  public static void main(String[] args) {
    ChatServer server = new ChatServer(SERVER_TEST_PORT);
    server.execute();
  }

  final int port;
  Set<UserThread> users;

  ChatServer(int port) {
    this.port = port;
    this.users = Collections.synchronizedSet(new HashSet<>());
  }

  void execute() {
    try {
      ServerSocket serverSocket = new ServerSocket(SERVER_TEST_PORT);

      while (true) {
        Socket clientSocket = serverSocket.accept();
        UserThread userThread = new UserThread(clientSocket, this);
        this.users.add(userThread);
        userThread.start();
      }
    } catch (IOException e) {
      System.err.println("Server couldn't start!");
      e.printStackTrace();
    }

  }

  void broadcast(UserThread user, String msg) {
    this.users.stream().filter(e -> e != user).forEach(e -> e.sendMessage(msg));
  }

  void remove(UserThread user) {
    this.users.remove(user);
  }

  List<String> getUsernames() {
    List<String> list = null;
    for (UserThread user : this.users) {
      list.add(user.getUsername());
    }
    return list;

//    this.users.stream().forEach(UserThread::getUsername);
//    return List.of("sdg", "sg");
  }
}
