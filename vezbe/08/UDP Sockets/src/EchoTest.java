import java.io.IOException;

public class EchoTest {
  public static void main(String[] args) {
    try (EchoServer server = new EchoServer(12345);
         EchoClient client = new EchoClient("localhost", 12345)) {
      server.start();

      String echo;
      echo = client.sendEcho("(test1) hello");
      System.out.println("(test1) returned: " + echo);

      echo = client.sendEcho("(test2) work");
      System.out.println("(test2) returned: " + echo);

      echo = client.sendEcho("(test3) \uD83D\uDE00");
      System.out.println("(test3) returned: " + echo);

      client.sendEcho("end");
    } catch (IOException e) {
      e.printStackTrace();
    }

    System.out.println("Test finished!");

  }
}
