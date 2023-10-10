import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class TrkaZaResursima2 {

//  static private int suma = 0;
  static private AtomicInteger suma = new AtomicInteger();
  static private final int LIMIT = 1000000;
  private static class Test implements Runnable{

    @Override
    public void run() {
      for (int i = 0; i < LIMIT; i++) {
//        suma++; // nije atomicka operacija; jedna vrednost ce da pregazi drugu
        suma.incrementAndGet();
      }

//      System.out.println(suma);
    }
  }

  /*
  *   NIJE ATOMICKA OPERACIJA
  *   ATOMICKA OPERACIJA = ILI CE SE SVE IZVRSITI ILI NISTA
  * */
  public static void main(String[] args) throws InterruptedException {
    int MAX_TH = 5;

    ArrayList<Thread> threads = new ArrayList<>();

    for (int i = 0; i < MAX_TH; i++) {
      Thread t = new Thread(new Test());
      t.start();
      threads.add(t);
    }

    for (Thread t : threads)
      t.join();

    System.out.println(suma.get());
  }
}
