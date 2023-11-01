import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class Main {
  public static void main(String[] args) {
    try {
      InetAddress addr4 = InetAddress.getByName("google.com");
      System.out.println(addr4.getHostAddress());
      printAddress(addr4.getAddress());
      System.out.println("IPv" + getVersion(addr4));
      System.out.println();

      InetAddress addr6 = InetAddress.getByName("ipv6.google.com");
      System.out.println(addr6.getHostAddress());
      printAddress(addr6.getAddress());
      System.out.println("Ipv" + getVersion(addr6));

    } catch (UnknownHostException e) {
      throw new RuntimeException(e);
    }
  }

  private static int getVersion(InetAddress addr) {
    /*
      Strasno neefikasno
    if (addr instanceof Inet4Address)
      return 4;
    else if (addr instanceof Inet6Address)
      return 6;
    else
      return -1;*/

    switch (addr.getAddress().length) {
      case 4: return 4;
      case 16: return 6;
      default: return -1;
    }
  }

  private static void printAddress(byte[] addr) {
    if (addr.length != 4)
      throw new IllegalArgumentException("This method works only for v4 addresses!");

    for (byte b : addr) {
      int ubyte = b < 0 ? b+256 : b;
      System.out.print(ubyte + " ");
    }
    System.out.println();

  }
}
