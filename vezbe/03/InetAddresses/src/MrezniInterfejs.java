import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Comparator;
import java.util.Enumeration;

public class MrezniInterfejs {
  /***
   *  Mrezni Interfejs = spona izmedju hosta i mreze
   *    Mogu biti softverski implementirani ili hardverski implementirani
   *                (virtuelni)                     (fizicki)
   *
   */
  public static void main(String[] args) {
//    NetworkInterface
    try {
      InetAddress local = InetAddress.getLoopbackAddress();
      System.out.println(NetworkInterface.getByInetAddress(local));

      Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();

      while (interfaces.hasMoreElements()) {
        NetworkInterface ni = interfaces.nextElement();
        System.out.println(ni);
        System.out.println("\t" + ni.getName() + " " + ni.getDisplayName() + " "
                + ni.getIndex());

        Enumeration<InetAddress> addresses = ni.getInetAddresses();
        while (addresses.hasMoreElements()) {
          System.out.println("\t\t" + addresses.nextElement());
        }
      }
    } catch (SocketException e) {
      throw new RuntimeException(e);
    }

    System.out.println("-----------------------------------------------");

    try {
      NetworkInterface.networkInterfaces()
              .sorted(Comparator.comparingInt(NetworkInterface::getIndex))
              .map(ni -> ni.toString() + "\n\t" + ni.getName() + " " +
                      ni.getDisplayName() + " " + ni.getIndex() + "\n" +
                      ni.inetAddresses().map(InetAddress::toString).reduce((acc, ip) -> acc + "\n\t\t" + ip)
                              .orElse("no addresses"))
              .forEach(System.out::println);
    } catch (SocketException e) {
      throw new RuntimeException(e);
    }
  }

}
