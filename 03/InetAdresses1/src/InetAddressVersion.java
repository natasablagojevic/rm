import java.net.InetAddress;

public class InetAddressVersion {

  private static int getVersion(InetAddress addr) {
    int len = addr.getAddress().length;
    if (len == 4)
      return 4;
    if (len == 16)
      return 6;
    return -1;
  }

  private static void printAddress(byte[] addr) {
    String str = "";

    for (byte b : addr) {
      if (b < 0) {
        str += (b+256) + ".";
      } else
        str += b + ".";
    }

    System.out.println(str.substring(0,str.length()-1));
  }
  public static void main(String[] args) {
    /*
     * Lokalni DNS
     *
     */

    try {
      InetAddress addressv4 = InetAddress.getByName("google.com");
      System.out.println(addressv4.getHostAddress());
      printAddress(addressv4.getAddress());
      System.out.println("IPv" + getVersion(addressv4));
      System.out.println();
      InetAddress addressv6 = InetAddress.getByName("ipv6.google.com");
      System.out.println(addressv6.getHostAddress());
      printAddress(addressv6.getAddress());
      System.out.println("IPv" + getVersion(addressv6));
    } catch (Exception e) {
      e.printStackTrace();
    }

    /*
     * Kako da proverimo da ne postoji neka adresa:
     *  Baca Exception ako ona ne postoji => ako ne postoji uradimo nesto ...
     *
     */
  }
}
