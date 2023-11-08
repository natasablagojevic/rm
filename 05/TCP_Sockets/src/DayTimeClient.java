import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class DayTimeClient {
  public static void main(String[] args) {
    try {
      Socket socket = new Socket("localhost", DayTimeServer.DEFAULT_PORT);
      var in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

      String line;
      while ((line = in.readLine()) != null) {
        System.out.println(line);
      }

      in.close();
      socket.close();

    } catch (UnknownHostException e) {
      throw new RuntimeException(e);
    } catch (IOException e) {
      System.err.println("Error while connecting on port " + DayTimeServer.DEFAULT_PORT);
      e.printStackTrace();
    }
  }
}
