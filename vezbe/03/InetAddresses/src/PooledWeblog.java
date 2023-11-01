import jdk.dynalink.linker.support.Lookup;

import java.io.*;
import java.nio.Buffer;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


/**
 * BlockingQueue je okej, ali nece biti svaki put super,
 * kada treba nesto da implementiramo.
 * Radimo sa signalima, davacemo signale kada dodajemo po-
 * slove, kada uklanjamo poslove...
 */
public class PooledWeblog {
  private final BufferedReader in;
  private final BufferedWriter out;
  private final int numOfThreads;
  private boolean finished;

  private final List<String> entries; // lista poslova

  PooledWeblog(InputStream in, PrintStream out, int threads) {
    this.in = new BufferedReader(new InputStreamReader(in));
    this.out = new BufferedWriter(new OutputStreamWriter(out));
    this.numOfThreads = threads;

    // TODO
     this.entries = Collections.synchronizedList(new LinkedList<>()); // ono sto nije sinhronizovano je obilazak
  }
  public void processLogFile() {
    /**
     * Pokreni sve worker-e; worker-i su oni koji su Runnable (klasa koja
     * implementira Runnable). Oni primaju this objekat (log). Korz log
     * ce dobiti pistup entries strukturi i da li je posao gotov (finished), kao
     * i pristup funkciji log koja vrsi log-in
     */
    for (int i = 0; i < this.numOfThreads; i++) {
      Thread t = new Thread(new LookupRunnable(this));
      t.start();
    }

    /**
     * Moramo da procitamo linije ovog fajla.
     *
     */
    try {
      // linija po linija i ubacujemo je u spisak poslova
      for (String entry = in.readLine(); entry != null; entry = in.readLine()) {
        while (this.entries.size() > this.numOfThreads) {
          Thread.sleep(1000/this.numOfThreads);
        }
        synchronized (this.entries) {
          this.entries.add(0, entry);
          this.entries.notifyAll();
        }

        this.finished = true;
        this.entries.notifyAll();
      }
    } catch (IOException | InterruptedException ex) {
      ex.printStackTrace();
    }
  }

  void log(String entry) throws IOException {
    this.out.write(entry);
    this.out.newLine();
    this.out.flush();
  }

  List<String> getEntries() {return this.entries;}
  boolean isFinished() {return this.finished;}
}
