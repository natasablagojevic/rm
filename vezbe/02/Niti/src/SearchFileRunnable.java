import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;

public class SearchFileRunnable implements Runnable{
  private final BlockingQueue<Path> queue;
  private final String keyword;

  SearchFileRunnable(BlockingQueue<Path> queue, String keyword) {
    this.queue = queue;
    this.keyword = keyword;
  }


  @Override
  public void run() {
    try {
      boolean done = false;
      while (!done) {
        Path p = this.queue.take(); // uzmemo iz reda
        if (p == FileTreeWalkerRunnable.END_OF_WORK) {
          done = true;
          this.queue.put(p);
        } else
          search(p); // pretrazimo
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    System.err.println("Thread " + Thread.currentThread() + " stopping...");
  }

  private void search(Path f) {
    try (Scanner sc = new Scanner(f)) {
      for (int ln = 1; sc.hasNextLine(); ln++) {
        String line = sc.nextLine();
        if (line.contains(this.keyword))
          System.out.println(f.getFileName() + ":" + ln + "\n");
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
