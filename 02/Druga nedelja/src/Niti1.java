public class Niti1 {

  // Thread
  private static class MyThread extends Thread {
    @Override
    public void run() {
      try {
        // cekanje 2s
        Thread.sleep(2000);
        System.out.println(Thread.currentThread());
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  private static class MyRunnable implements Runnable {

    @Override
    public void run() {
      try {
        Thread.sleep(2000);
        System.out.println(Thread.currentThread());
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  public static void main(String[] args) throws InterruptedException {

//    System.out.println(Thread.currentThread());

    /*
    *   Pravi se nova nit, i njoj je potrebno vreme da se napravi
    *
    *   *join* = sacekaj sa izvrsavanjem, prvo treba da se saceka
    *   sa izvrsavanjem t1, pa onda kreni sa izvrsavanjem t2
    *
    *
    *  */

    Thread t1 = new MyThread();
//    t1.run(); // samo se izvrsava deo koda u run metodi koja se nalazi u klasi MyThread
    t1.start(); // pravi se nova nit
//    System.out.println(t1);

    try {
      t1.join();
    } catch (Exception e) {
      e.printStackTrace();
    }


    // Preko ovoga se pravi nova nit
    Runnable r = new MyRunnable();
//    r.run();

    Thread t2 = new Thread(r);
    t2.start();

    System.out.println("Doslo se dovde");

    //elegatnije
    Thread t3 = new Thread(new Runnable() {
      @Override
      public void run() {
        // ...
      }
    });

    Thread t4 = new Thread(() -> {});

    // wait()
    // await()


  }
}
