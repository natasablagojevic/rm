import java.io.*;
import java.net.Socket;

public class ClientHandlerRunnable  implements Runnable{
  private Socket client;
  ClientHandlerRunnable(Socket client) {
    this.client = new Socket(client);
  }
  @Override
  public void run() {
    try {
      BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
      BufferedWriter out = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
      String line;

      while ((line = in.readLine()) != null) {
        System.err.println("Line read: " + line);
        out.write(line);
        out.newLine();
        out.flush();
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (client != null)
        try {
          client.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
    }
  }
}
