import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class UnknownContentType {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    while (sc.hasNext()) {
      try {
        String line = sc.nextLine();
        if (line.trim().equals(""))
          continue;

        if (line.equalsIgnoreCase("exit"))
          break;

        // HEAD REQUEST

        URL u = new URL(line);
        URLConnection conn = u.openConnection();

        System.out.println("-------------------------");

        System.out.println(conn.getHeaderFieldKey(0));

        for (int i = 1; ; i++) {
          String value = conn.getHeaderField(i);

          if (value == null)
            break;

          String key = conn.getHeaderFieldKey(i);
          System.out.println(key + " : " +  value);
        }
        System.out.println("-------------------------");

//        throw new IOException("...");
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    sc.close();
  }
}
