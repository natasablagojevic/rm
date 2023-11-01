import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

public class InetAddressIntro {
  public static void main(String[] args) {

    try {
      InetAddress addr = InetAddress.getByName("www.matf.bg.ac.rs");
      InetAddress v4addr;
      InetAddress v6addr;

      System.out.println(InetAddress.getByName("www.google.com"));
      System.out.println(InetAddress.getByName("www.facebook.com"));
      System.out.println(InetAddress.getByName("www.v6.facebook.com"));
      System.out.println("-------------------------------------------------");
      System.out.println(Arrays.toString(InetAddress.getAllByName("www.v6.facebook.com")));
      System.out.println("-------------------------------------------------");
      System.out.println(Arrays.toString(InetAddress.getAllByName("google.com")));
      System.out.println("-------------------------------------------------");
      System.out.println(InetAddress.getLocalHost());
      System.out.println(InetAddress.getLoopbackAddress());
      System.out.println("-------------------------------------------------");
      System.out.println(InetAddress.getByName("www.matf.bg.ac.rs"));
      System.out.println(InetAddress.getByName("www.math.rs").getAddress());
      System.out.println("-------------------------------------------------");

      InetAddress matf = InetAddress.getByName("www.matf.bg.ac.rs");
      InetAddress math = InetAddress.getByName("www.math.rs");

      // ne poredi hostname, vec IP adrese
      System.out.println(matf.equals(math));

    } catch (UnknownHostException e) {
      throw new RuntimeException(e);
    }

  }
}
