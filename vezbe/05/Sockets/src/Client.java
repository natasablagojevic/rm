import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

  public static void main(String[] args) {
    try (Socket client = new Socket("localhost", Server.PORT);
         BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()))){

      String time = in.readLine();
      System.out.println("Sent: " + time );


    } catch (UnknownHostException e) {
      throw new RuntimeException(e);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

}
