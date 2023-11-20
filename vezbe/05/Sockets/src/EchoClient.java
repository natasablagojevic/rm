import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class EchoClient {
  public static void main(String[] args) {
    String host = "localhost";

    System.err.println("Connecting to " + host);




    try (Socket client = new Socket(host, EchoServer.PORT);
         BufferedReader networkIn = new BufferedReader(new InputStreamReader(client.getInputStream()));
         BufferedWriter networkOut = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
         BufferedReader consoleIn = new BufferedReader(new InputStreamReader(System.in))) {

      while (true) {

        // reading from stdin
        String line = consoleIn.readLine().trim();

        if (line.equalsIgnoreCase("exit")) {
          break;
        }

        // send to server
        networkOut.write(line);
        networkOut.newLine();
        networkOut.flush();

        // recive echo
        String echo = networkIn.readLine();
        System.out.println(echo);
      }



    } catch (UnknownHostException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
