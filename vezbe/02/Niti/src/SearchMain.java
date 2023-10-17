import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class SearchMain {
  private static final int FILE_QUEUE_SIZE = 10;
  private static final int THREAD_NUM = 5;

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    System.out.println("Enter base dir: ");
    String dir = sc.nextLine();
    System.out.println("Enter keyword: ");
    String keyword = sc.nextLine();
    sc.close();

    /*
     *  Brojimo koliko imamo aktivnih niti, svaki put kada nit zavrsi
     *  smanjimo brojac;
     */

    // Perform thread search
    // TODO
    BlockingQueue<Path> fileQueue = new ArrayBlockingQueue<>(FILE_QUEUE_SIZE);

    // Pretraga direktorijuma:
    var file = new FileTreeWalkerRunnable(Paths.get(dir), fileQueue);
    new Thread(file).start();

    // Start workers
    for (int i = 0; i < THREAD_NUM; i++) {
      new Thread(new SearchFileRunnable(fileQueue, keyword)).start();
    }

  }
}
