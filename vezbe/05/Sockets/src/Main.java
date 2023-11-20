import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;

public class Main {
  public static void main(String[] args) {

    String host = "localhost";
    // String host = "www.matf.bg.ac.rs";

    System.out.println("Start time: " + new Date());

    // u Javi nema unsigned short!
    for (int i = 1; i < 65536; i++) {
      System.out.printf("\rTesting port: %5d", i);

      try (Socket s = new Socket(host, i)) {

        System.out.println("\nSocket data: " + s);
        System.out.println("Found @ " + new Date());

        s.close(); // auto-closable => try-with-resources
      } catch ( UnknownHostException e) {
        // ako nisam uspeo jednom da ga opsluzim, necu nikad ni moci
//        e.printStackTrace();
        break;
      } catch (IOException e) {
        // nemamo servera koji slusa na ovom portu
        // necemo uraditi nista

        // inored
//        e.printStackTrace();
      }

//      System.out.println("---------------------------------------------");
    }

    System.out.println("\nEnd time: " + new Date());

/*
    // ServerSocket za serverske aplikacije
    int port = 9000;
    try (ServerSocket ss = new ServerSocket(port)) {
      ss.bind(new InetSocketAddress(port));
      ss.bind(port); // navodimo port eksplicitno; a taj port moraju da zna i kljent

      *//**
       * ceka (blokira), dok se ne ostvari veza sa nekim klijentom i vraca Socket koji
       * predstavlja vezu
       *//*
      ss.accept();

    } catch (IOException e) {
      throw new RuntimeException(e);
    }*/

  }
}