import java.io.FileInputStream;
import java.io.FileOutputStream;

public class ImageCopy {
  public static void main(String[] args) {
    try {
      FileInputStream in = new FileInputStream("in.PNG");
      FileOutputStream out = new FileOutputStream("out.PNG");

      int b;
      while ((b = in.read()) != -1)
        out.write(b);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
