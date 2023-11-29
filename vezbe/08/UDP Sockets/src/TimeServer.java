import javax.xml.crypto.Data;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TimeServer {
  public static final int PORT = 12345;
  public static final int BUF_SIZE = 1024;
  private static final Logger err = Logger.getLogger("errors");
  private static final Logger audit = Logger.getLogger("requests");


  public static void main(String[] args) {
    try (DatagramSocket socket = new DatagramSocket(PORT)) {

      while (true) {
        DatagramPacket request = new DatagramPacket(new byte[BUF_SIZE], BUF_SIZE);
        socket.receive(request);

        audit.info("Request received from client. ");

        String dayTime = new Date().toString();
        byte[] data = dayTime.getBytes(StandardCharsets.UTF_8);

        try {
          DatagramPacket response = new DatagramPacket(data, data.length, request.getAddress(), request.getPort());
          socket.send(response);

          audit.info("Sent response to client.");
        }  catch (IOException e) {
          err.log(Level.SEVERE, e.getMessage(), e);
        }
      }


    } catch (IOException e) {
     err.log(Level.SEVERE, e.getMessage(), e);
    }
  }

}
