import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

public class LookupRunnable implements Runnable{

  private final PooledWeblog log;
  private final List<String> entries;

  LookupRunnable(PooledWeblog log) {
    this.log = log;
    this.entries = log.getEntries();
  }

  private String getNextEntry() {


    while (true) {
      if (this.entries.size() == 0) {
        String entry = this.entries.remove(0);
      } else {
        if (this.log.isFinished()) {
          return null;
        }
      }
    }
  }

  // vadjenje iz liste
  private String analyzeEntryAndGetResult(String entry) throws UnknownHostException {
    int ind = entry.indexOf(" ");
    String addrStr = entry.substring(0, ind);
    InetAddress addr = InetAddress.getByName(addrStr);

    return addr.toString() + entry.substring(ind);
  }

  @Override
  public void run() {
    String entry = getNextEntry();
    while (entry != null) {
      try {
        String result = analyzeEntryAndGetResult(entry);
        this.log.log(result);

      } catch (IOException e) {
        System.err.println("Iznenadno se zavrsava rad niti...");
        break;
      }


      entry = getNextEntry();
    }
  }
}
