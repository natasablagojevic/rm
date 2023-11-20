import org.xml.sax.InputSource;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Date;

public class Client {
  public static void main(String[] args) {
    String hostname = "time.nist.gov";
    int port = 37;
    long diffInEpochsSeconds = 2208988880L;

    try (Socket client = new Socket(hostname, port)) {

      // citanje bajtova
      InputStream in = new BufferedInputStream(client.getInputStream());

//      byte []buf = in.readNBytes(4);
//      System.out.println(Arrays.toString(buf));

      long secondsSince1990 = 0;
      for (int i = 0; i < 4; i++) {
        int b = in.read();
        secondsSince1990 = (secondsSince1990 << 8) | b;
//        secondsSince1990 <<= 8;
//        secondsSince1990 |= b;


//        System.out.printf("%x ", b);
      }

      long secondsSince1970 = secondsSince1990 - diffInEpochsSeconds;
      long milisSince1070 = secondsSince1970*1000;
      Date now = new Date(milisSince1070);
      System.out.println("It is " + now + " @ " + hostname);

      System.out.println(secondsSince1990);

    } catch (UnknownHostException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
