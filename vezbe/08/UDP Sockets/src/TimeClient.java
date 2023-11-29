import javax.xml.crypto.Data;
import java.io.IOException;
import java.io.Serial;
import java.net.*;

public class TimeClient {
  public static void main(String[] args) {
    String hostname = "localhost";
    try (DatagramSocket socket = new DatagramSocket()) {
      socket.setSoTimeout(5000);

      InetAddress host = InetAddress.getByName(hostname);
      DatagramPacket request = new DatagramPacket(new byte[1], 1, host, TimeServer.PORT);

      socket.send(request);

      DatagramPacket response = new DatagramPacket(new byte[TimeServer.BUF_SIZE], TimeServer.BUF_SIZE);
      socket.receive(response);

      String date = new String(response.getData());
      System.out.println(date);

    } catch (SocketException e) {
      e.printStackTrace();
    } catch (UnknownHostException e) {
      e.printStackTrace();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
