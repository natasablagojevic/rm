import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Arrays;
import java.util.Enumeration;

/**
 * Nece biti zadataka iz ovoga.. nece davati nikola
 */
public class LoopBackInterface {
  public static void main(String[] args) throws SocketException {
//    NetworkInterface.networkInterfaces()  // vraca string svih interfejsa
    try {
      Enumeration<NetworkInterface> nis = NetworkInterface.getNetworkInterfaces();

      while (nis.hasMoreElements()) {
        NetworkInterface ni = nis.nextElement();
        System.out.println(ni);

        System.out.println(ni.getDisplayName() + " " +
                ni.getIndex() + " " );

        Enumeration<InetAddress> iaddr = ni.getInetAddresses();
        while (iaddr.hasMoreElements()) {
          System.out.println(iaddr.nextElement());
        }
        System.out.println("-----------------------------------------");

        InetAddress lo = InetAddress.getLoopbackAddress(); // za debagovanje
        System.out.println(NetworkInterface.getByInetAddress(lo).getDisplayName());
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
