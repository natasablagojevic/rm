public class RaceCondition {

  static int x = 0;
  static final int LIMIT = 100000000;
  static final int NUM_THREADS = 100000000;
  static class MyThread implements Runnable {

    @Override
    public void run() {
      // nedeterministicki kod
      for (int i = 0; i < LIMIT; i++)
        x++; // ova operacija nije atomicka
             // mogu i drugi procesi da joj pristupe, stoga treba da
             // zastitimo kriticnu sekciju
    }
  }
  public static void main(String[] args) throws InterruptedException {
    for (int i = 0; i < NUM_THREADS; i++) {
      new Thread(new MyThread()).start();
    }

    Thread.sleep(5000);
    System.out.println(x);
  }
}
