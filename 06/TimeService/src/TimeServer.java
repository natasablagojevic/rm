import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class TimeServer {
  public final static int DEFAULT_PORT = 4242;
  public static void main(String[] args) {
    long diff = 220898880L;

    Date now = new Date();
    long from1990 = now.getTime() - diff;

    // opsluzivanje klijenata koliko god da ih je
    try {
      ServerSocket server = new ServerSocket(DEFAULT_PORT);

      while (true) {
        try (Socket client = server.accept()) {
          BufferedOutputStream out = new BufferedOutputStream(client.getOutputStream());
          byte []arr = new byte[4];
          arr[0] = (byte)(from1990 >> 24);
          arr[1] = (byte)(from1990 >> 16);
          arr[2] = (byte)(from1990 >> 8);
          arr[3] = (byte)(from1990);

          out.write(arr);
          out.flush();

        } catch (IOException e) {
          System.err.println("Greska na strani klijenta");
        }
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
