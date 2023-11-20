import java.io.*;
import java.net.Socket;

public class ClientHandlerRunnable implements Runnable {
  private final Socket client;

  public ClientHandlerRunnable(Socket client) {
    this.client = client;
  }

  @Override
  public void run() {
    // citamo liniju po liniju
    try (BufferedReader in = new BufferedReader(new InputStreamReader(this.client.getInputStream()));
         BufferedWriter out = new BufferedWriter(new OutputStreamWriter(this.client.getOutputStream()))){

      String line = null;
      while ((line = in.readLine()) != null) {
        out.write(line);
        out.newLine();
        out.flush();
      }
    } catch (IOException e) {
      System.err.printf("Client handler [%2d] errored!\n", Thread.currentThread().getId());
      e.printStackTrace();
    } finally {
      try {
        this.client.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    System.err.printf("Client handler [%2d] exited!\n", Thread.currentThread().getId());
  }
}
