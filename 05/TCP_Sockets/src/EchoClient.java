import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class EchoClient {
  public static void main(String[] args) {
    String hostname = "localhost";

    // procitamo sa stdin
    // posaljemo serveru
    // procitamo od servera
    // ispisemo na stdout

    try (Socket socket = new Socket(hostname, EchoServer.DEFAULT_PORT);
         BufferedReader consoleIn = new BufferedReader(new InputStreamReader(System.in));
         BufferedReader socketIn = new BufferedReader(socket.getInputStream());
         BufferedWriter socketOut = new BufferedWriter(socket.getOutputStream())
    ) {

      while (true) {
        String line = consoleIn.readLine();

        if (line.trim().equalsIgnoreCase("exit"))
          break;

        socketOut.write(line);
        socketOut.newLine();
        socketOut.flush();

        // citanje od servera odgovor

        String fromServer = socketIn.readLine();
        System.out.println(fromServer);


      }


    } catch (IOException | UnknownHostException e) {
      e.printStackTrace();
    }


  }
}
