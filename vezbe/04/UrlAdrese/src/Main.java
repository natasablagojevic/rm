import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
  public static void main(String[] args) {
    // URL - kao putanja u fajl serveru
    try {
      URL url = new URL("https://hjsdgfjshdf");
      System.out.println(url.openStream()); // otvara oknekciju ka hostu; vrac stream do tog resursa

      InputStream in = url.openStream();
      in.read();

      // preko ovoga se radi post
      URLConnection conn =  url.openConnection(); // koristi se da nam otvori vezu do tog resursa
      //
    } catch (MalformedURLException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

  }
}