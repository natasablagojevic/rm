/*

    Umesto da nasledjujemo klasu Thread, mozemo da implementiramo
    interfejs *Runnable*

    Zbog nasledjivanja, mozemo da implementiramo smo jedno od ova dva nacina,
    jer u Javi postoji samo jednostruko nasledjivanje.



 */

public class Main1 {
  static class MyThread extends Thread {
    @Override
    public void run() {

      try {
        Thread.sleep(5000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

      System.out.println(Thread.currentThread().getId());
    }

  }

  static class MyThreadRunnable implements Runnable {

    @Override
    public void run() {

      try {
        Thread.sleep(5000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println(Thread.currentThread().getId());
    }
  }

  public static void main(String[] args) {

    /*

     */
    System.out.println(Thread.currentThread().getId());
    Thread t = new MyThread();
    //new MyThread().run(); // mozemo ovo da uradimo ali nema neke poente
    t.start();
    new Thread(new MyThreadRunnable()).start();

    /*
    *   Kod RUNNABEL = run se nece izvrsiti u zasebnoj niti;
    *   metod start ne postoji u RUNNABEL
    * */

    // ako zelimo da prekinemo, kako to da uradimo?
    // kako nasilno prekinuti nit koja ne radi na ovaj nacin?
    // metod stop nad Thread-om vrsi to nasilno prekidanje
    // clean-up ako radimo, zatvaranje fajlova i slicno, da damo signal nitima da to urade
    try {
      t.join();
    } catch (Exception e) {
      e.printStackTrace();
    }

    System.out.println("Finishing main...");    // pre ovoga je potrebno join

  }
}
