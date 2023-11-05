import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.net.MalformedURLException;
public class SourceReader {
  private static final String URL_STRING = "http://poincare.matf.bg.ac.rs/~ivan.ristovic";
  public static void main(String[] args) throws IOException {
    URL u = new URL(URL_STRING);

    /***
     * Kada radimo bez Connection-a moramo da znamo koliko citamo
     * Kada koristimo BufferedReader, on moze da nam da liniju po liniju tekst
     */
    try (BufferedReader in = new BufferedReader(
            new InputStreamReader(u.openStream())
    )) {
      String line;
      while ((line = in.readLine()) != null) {
        System.out.println(line);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    System.out.println("---------------------------------------------");
    /***
     * NOTE: uvek je bolje raditi sa URL connection-om
     */

    URLConnection conn = u.openConnection();
    String encoding = conn.getContentEncoding();

    if (encoding == null) {
      encoding = "UTF-8";
    }

    try (BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), encoding))) {
      String line;
      while ((line = in.readLine()) != null) {
        System.out.println(line);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
