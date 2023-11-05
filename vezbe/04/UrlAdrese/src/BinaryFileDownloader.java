import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.*;

public class BinaryFileDownloader {
  public static void main(String[] args) {
    try {
      URL url = new URL("http://www.matf.bg.ac.rs/images/matf.gif");

      // lakse je raditi sa Connection, jer treba proveriti
      // takodje neke stvari
      URLConnection conn = url.openConnection();

      String contentType = conn.getContentType();
      int contentLength = conn.getContentLength();
      String path = url.getPath();
      String fileName = path.substring(path.lastIndexOf('/')+1);

      if (contentLength == -1 || contentType.startsWith("text")) {
        throw new UnsupportedOperationException("Content is not a binary file");
      }

      System.out.println("Downloading " + fileName + " .... ");

      // korisitmo BufferedInputStream zbog toga ovo sto skidamo nije
      // tekstualni fajl
      try (BufferedInputStream in = new BufferedInputStream(conn.getInputStream()) ) {
        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(fileName));

        for (int i = 0; i < contentLength; i++) {
          int b = in.read();
          out.write(b);
        }

        System.out.println("Finished succesfully!");

      } catch (Exception e) {
        e.printStackTrace();
      }


    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
