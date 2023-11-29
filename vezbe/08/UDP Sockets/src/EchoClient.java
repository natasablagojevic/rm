import javax.xml.crypto.Data;
import java.io.Closeable;
import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class EchoClient implements Closeable {
  private final DatagramSocket socket;
  private final InetAddress address;
  private final int port;

  public static void main(String[] args) throws IOException {
    var client = new EchoClient("localhost", 12345);
    System.out.println(client.sendEcho("helllo"));
  }

  public EchoClient(String hostname, int port) throws SocketException, UnknownHostException {
    this.socket = new DatagramSocket();
//    this.socket.setSoTimeout(5000);
    this.address = InetAddress.getByName(hostname);
    this.port = port;
  }

  public String sendEcho(String msg) throws IOException {
    byte[] buf = msg.getBytes(StandardCharsets.UTF_8);

    System.err.println("Client send: " + msg + " " + Arrays.toString(buf));
    DatagramPacket request = new DatagramPacket(buf, buf.length, this.address, this.port);
    this.socket.send(request);

    DatagramPacket response = new DatagramPacket(buf, buf.length);
    this.socket.receive(response);
    System.err.println("Client recv: " + Arrays.toString(response.getData()));

    return new String(response.getData(), StandardCharsets.UTF_8);
  }

  @Override
  public void close() throws IOException {
    this.socket.close();
  }
}
