import java.net.InetAddress;
import java.util.Arrays;

public class InetAdressIntro {
  public static void main(String[] args) {

    /*
     * DNS server = menjamo ga u podesavanjima; menja se odakle cemo pretrazivati tabelu
     * DNS preslikavanje
     *
     */
    try {
      // buni se - exception
      InetAddress addr = InetAddress.getByName("www.matf.bg.ac.rs");
      System.out.println(addr);
      // uzimanje svih adresa
      System.out.println(Arrays.toString(addr.getAddress()));

      InetAddress addr2 = InetAddress.getByName("www.math.rs");
      System.out.println(addr2);
      System.out.println(Arrays.toString(addr2.getAddress()));
      System.out.println(addr2.equals(addr));

      // LocalHost
      InetAddress localHost = InetAddress.getLocalHost();
      System.out.println(localHost);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
