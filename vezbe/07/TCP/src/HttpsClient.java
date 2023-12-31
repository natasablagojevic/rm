import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;
import java.net.UnknownHostException;

/**
 * NE RADI SE NA ISPITU!!!
 * source_server
 * handshake
 */
public class HttpsClient {
  public static void main(String[] args) {
    String host = "example.com";
    int port = 443;

    try {
      SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
      SSLSocket socket = (SSLSocket) factory.createSocket(host, port);

      String[] supported = socket.getSupportedCipherSuites();
      socket.setEnabledCipherSuites(supported);
      Writer out = new OutputStreamWriter(socket.getOutputStream());

      /**
       * HTTPS requires the full URL in the first HTTP header
       * '\r\n' is the CRLF (new line) after HTTP headers
       */
      out.write("GET https://" + host + "/ HTTP1.1\r\n");
      out.write("Host: " + host + "\r\n");
      out.write("Accept: text/plain\r\n");
      out.write("\r\n");
      out.flush();

      BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

      System.out.println("--- begin response ---");
      String line;
      while ((line = in.readLine()) != null) {
        System.out.println(line);
      }

      System.out.println("--- end response ---");

      out.close();
      in.close();
      socket.close();

    } catch (UnknownHostException e) {
      throw new RuntimeException(e);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
