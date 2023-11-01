import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class PooledWeblog {
  private final BufferedReader in;
  private final BufferedWriter out;
  private final int numOfThreads;
  private boolean finished;
  private final List<Thread> workers;

  private final List<String> entries = Collections.synchronizedList(new LinkedList<>());

  PooledWeblog(InputStream in, PrintStream out, int threads) {
    this.in = new BufferedReader(new InputStreamReader(in));
    this.out = new BufferedWriter(new OutputStreamWriter(out));
    this.numOfThreads = threads;
    this.workers = new ArrayList<>(this.numOfThreads);
    this.finished = false;
  }

  void porocessLogFile() {
    for (int i = 0; i < this.numOfThreads; i++) {
      workers.add(new Thread(new LookupRunnable(this)));
      workers.get(i).start();
    }

    while (true) {
      try {
        // kada ima vise niti da se manje ceka
        while (this.entries.size() > this.numOfThreads) {
          Thread.sleep(1000 / this.numOfThreads);
        }

        String line = in.readLine();

        if (line == null) {
          System.out.println("Uspesan kraj");
          this.finished = true;
          break;
        }

        this.entries.add(0, line);
        this.entries.notifyAll(); // probuditi niti svaku, ravnopravnu sansu imaju da se probude

      } catch (InterruptedException e) {
        System.err.println("Desio se interapt! Kraj izvrsavanja");
        this.finished = true;
      } catch (IOException e) {
        System.err.println("Desila se IO Greska! Kraj izvrsavanja..");
        this.finished = true;
      }
    }
  }

  public void log(String entry) throws IOException {
    // citaci i pisaci su sinhronizovani
    this.out.write(entry);
    this.out.newLine();
    this.out.flush();
  }

  boolean isFinished() {
    return this.finished;
  }

  List<String> getEntries() {
    return this.entries;
  }

  @Override
  public void close() throws Exception {
    this.in.close();

    // We have to wait for workers to finish in order to close the output stream (they use it for logging)
    for (Thread worker : this.workers) {
      worker.join();
    }
    this.out.close();
  }
}
