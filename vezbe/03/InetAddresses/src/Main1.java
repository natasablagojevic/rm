import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

public class Main1 {
  public static void main(String[] args) {
    try (BufferedReader in = new BufferedReader(new InputStreamReader(System.in))) {
      System.out.println("Enter name/addresses or \"exit\" to quit ");

      while (true) {
        String host = in.readLine();
        if (host.equalsIgnoreCase("exit") || host.equalsIgnoreCase("quit"))
          break;

        System.out.println(lookUp(host));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private static String lookUp(String host) {
    InetAddress addr;
    try {
      addr = InetAddress.getByName(host);
    } catch (UnknownHostException e) {
      return "Cannot find host " + host;
    }
    if (isHostName(host))
      return addr.getHostAddress();
    else
      return addr.getHostName();

  }

  private static boolean isHostName(String host) {
    // hostname
    // ipv4
    // ipv6

    if (host.indexOf(':') != -1)
      return false;

    String[] parts = host.split("\\.");
    if (parts.length != 4)
      return true;

    for (String part : parts) {
      for (char c : part.toCharArray())
        if (!Character.isDigit(c))
          return true;
    }

//    return Arrays.stream(parts).anyMatch(part -> part.chars().anyMatch(e -> !Character.isDigit(e)));

    return false;
  }
}
