import javax.xml.crypto.Data;
import java.io.Closeable;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class EchoServer extends Thread implements Closeable {
  public static void main(String[] args) throws SocketException {
    new EchoServer(12345).start();
  }

  private final DatagramSocket socket;

  public EchoServer(int port) throws SocketException {
    this.socket = new DatagramSocket(port);
  }

  @Override
  public void close() throws IOException {
    this.socket.close();
  }

  @Override
  public void run() {
    System.err.println("Server starting...");
    try {
      while (true) {
        byte[] buf = new byte[256];
        DatagramPacket request = new DatagramPacket(buf, buf.length);

        this.socket.receive(request);

        try {
          DatagramPacket response = new DatagramPacket(buf, request.getLength(), request.getAddress(), request.getPort());

          this.socket.send(response);
        } catch (IOException e) {
          e.printStackTrace();
        }

        String recieved = new String(buf); // TODO
        System.err.println("Server recv: " + recieved);

        if (recieved.equalsIgnoreCase("end")) {
          System.err.println("Server recv end signal");
          break;
        }
      }

    } catch (IOException e) {
      e.printStackTrace();
    }
    System.err.println("Server shutting down.");
  }
}
