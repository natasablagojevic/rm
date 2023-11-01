import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

public class LookupRunnable implements Runnable{
  private final PooledWeblog log;
  private final List<String> entries;

  LookupRunnable(PooledWeblog log) {
    this.log = log;

    // cuvamo referencu na red poslova
    this.entries = log.getEntries();
  }

  @Override
  public void run() {
    try {
      for (String entry = this.getNextEntry(); entry != null; entry = this.getNextEntry()) {
        String result = this.analyzeEntryAndGetResult(entry);

        if (result == null)
          continue;

        try {
          this.log.log(result);
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  /**
   * Vraca sledeci posao ili null ako nema sledeceg posla.
   *
   */
  private String getNextEntry() throws InterruptedException {
    synchronized (this.entries) {
      if (this.entries.size() == 0) {
        if (this.log.isFinished()) {
          System.err.println("Thread exiting..." + Thread.currentThread());
          return null;
        }

        // moramo da budemo u synhronized bloku nad
        // objektom da bismo radili wait
        this.entries.wait();
      }

      return this.entries.remove(0);
    }
  }

  /**
   * Uzmi konkretan posao i analiziraj sta treba da uradis.
   * Za datu liniju fajla radi Lookup.
   * Vraca null ako je linija nevalidna, ako nema hosta ili
   * tako nesto.
   */
  private String analyzeEntryAndGetResult(String entry) {
    int index = entry.indexOf(' ');

    if (index == -1)
      return null;

    String host = entry.substring(0, index);
    try {
      return InetAddress.getByName(host).getHostName();
    } catch (UnknownHostException e) {
      return host;
    }
  }
}
