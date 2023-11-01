import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class PooledWeblogTest {

  /**
   * Mozemo da pokrenemo vise niti, jer pasivno ceka.
   * Jer ako jedna od 15 adresa ne odgovara, ne zelimo da koci..
   * MultiThread aprouching ...
   *
   */

  public static void main(String[] args) throws FileNotFoundException {
    PooledWeblog pw = new PooledWeblog(
            new FileInputStream("/home/natasa/Desktop/Fakultet/4_god/7_sem/rm/vezbe/03/InetAddresses/apache.logfile"),
            System.out,
            6
    );

    pw.processLogFile();
  }
}
