import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Zadatak1 {
  /**
   * Obrada apache.logfile
   * @param args
   */


  public static void main(String[] args) throws Exception {
    PooledWeblog pw = new PooledWeblog(
            new FileInputStream("/home/natasa/Desktop/Fakultet/4_god/7_sem/rm/vezbe/03/InetAdresses1/apache.logfile"),
            System.out,
            6);

    pw.porocessLogFile();
    pw.close();
  }
}
