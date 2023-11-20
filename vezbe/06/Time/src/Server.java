import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class Server {
  public static void main(String[] args) {
    long diffInEpochs = 2208988880L;

    try (ServerSocket server = new ServerSocket(37)) {
      while (true) {
        try (Socket client = server.accept()) {
          Date now = new Date();
          long msSince1970 = now.getTime();
          long sSince1970 = msSince1970/1000;
          long sSince1990 = sSince1970 + diffInEpochs;

          byte []buf = new byte[4];
          buf[0] = (byte)(sSince1990 >> 24);
          buf[1] = (byte)(sSince1990 >> 16);
          buf[2] = (byte)(sSince1990 >> 8);
          buf[3] = (byte)(sSince1990);

          OutputStream out = new BufferedOutputStream(client.getOutputStream());
          out.write(buf);
          out.flush();
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
