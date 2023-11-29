//import java.io.IOException;
//import java.net.DatagramPacket;
//import java.net.DatagramSocket;
//import java.net.SocketException;
//
//public class Main {
//  public static void main(String[] args) {
//    try (DatagramSocket ds = new DatagramSocket()) {
//
//      DatagramPacket toSend = new DatagramPacket(new byte[512], 512, host, port);
//      ds.send(toSend);
//
//      DatagramPacket toRecive = new DatagramPacket(new byte[512], 512);
//      ds.receive(toRecive);
//
//    } catch (SocketException e) {
//      e.printStackTrace();
//    } catch (IOException e) {
//      e.printStackTrace();
//    }
//  }
//}
