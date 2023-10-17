import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.BlockingQueue;

final public class FileTreeWalkerRunnable implements Runnable{
  static final Path END_OF_WORK = Paths.get("");
  private final BlockingQueue<Path> queue;
  private final Path startingDir;
  public FileTreeWalkerRunnable(Path path, BlockingQueue<Path> fileQueue) {
    this.startingDir = path;
    this.queue = fileQueue;
  }

  @Override
  public void run() {
    try {
      this.walk(this.startingDir);
      this.queue.put(END_OF_WORK);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  private void walk(Path startingDir) {
    try (var ds = Files.newDirectoryStream(startingDir)){
      for (Path p : ds) {
        if (Files.isDirectory(p))
          walk(p);
        else
          this.queue.put(p);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
