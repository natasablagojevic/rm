import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class ImageCopyBuffered {
  public static void main(String[] args) {
    try {
      BufferedInputStream bin = new BufferedInputStream(new FileInputStream("in.PNG"));
      BufferedOutputStream bout = new BufferedOutputStream(new FileOutputStream("out.PNG"));

      byte[] buf = new byte[512];
      int readBytes = 0;

      while ((readBytes = bin.read(buf)) != -1)
        bout.write(buf, 0, readBytes);

      bin.close();
      bout.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
