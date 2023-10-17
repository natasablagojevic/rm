import java.util.concurrent.ThreadLocalRandom;

public class TransferRunnable implements Runnable{

  static final int MAX_TRANSFER_DELAY = 2;

  private IBank bank;
  private int from;
  private int max;

  TransferRunnable(IBank bank, int from, int max) {
    this.bank = bank;
    this.from = from;
    this.max = max;
  }

  @Override
  public void run() {

    ThreadLocalRandom r = ThreadLocalRandom.current();

    // ako se desi neka greska hocu da prekinem petlju skroz
    try {
      while (true) {
        int to = r.nextInt(bank.count());
        int account = r.nextInt(this.max);
        this.bank.transfer(this.from, to, account);
        Thread.sleep(MAX_TRANSFER_DELAY);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
