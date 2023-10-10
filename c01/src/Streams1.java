import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.zip.GZIPInputStream;

public class Streams1 {
  public static void main(String[] args) {
    try {
      OutputStream out;
      FileOutputStream fout = new FileOutputStream("1.txt");
      ByteArrayOutputStream bout;

      InputStream in;
      FileInputStream fin = new FileInputStream("1.txt");
      ByteArrayInputStream bin;
      GZIPInputStream gz;

      BufferedInputStream bfin = new BufferedInputStream(fin);
      BufferedOutputStream bfout = new BufferedOutputStream(fout);

      Reader r;
      Writer w;
      InputStreamReader sr = new InputStreamReader(fin, StandardCharsets.UTF_8);
      OutputStreamWriter sw = new OutputStreamWriter(fout, StandardCharsets.UTF_8);

      FileReader fi;
      FileWriter fw;

      sr.close();
      sw.close();

      try (InputStream sAutoClose = new FileInputStream("file.txt")) {
        // uradi nesto
      } catch (Exception e) {
        // obradi izuzetak
      } finally {
        // nije potrebno zatvoriti strimove, ovaj try-catch blok ce sam
        // drugacije se naziva try-with-resorces
      }

      PrintStream ps1 = new PrintStream(new FileOutputStream("file.txt"), false); // bez baferisanja
      PrintStream ps2 = new PrintStream(new BufferedOutputStream(new FileOutputStream("file.txt")), false); // ima baferisanje

      ps1.println("Hello");
      ps2.println("2");
      ps2.flush();


    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
