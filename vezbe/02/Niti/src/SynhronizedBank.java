import java.util.Arrays;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SynhronizedBank implements IBank {
  private final int[] accounts;
  private Lock lock;
  private final Condition insufficentFunds;

  LockBank(int accountNum, int initialBalance) {
    this.accounts = new int[accountNum];
    Arrays.fill(this.accounts, initialBalance);
    this.lock = new ReentrantLock();
    this.insufficentFunds = this.lock.newCondition();
  }

  /*
   * synhronized = ima dva efekta:
   *  1. ne mozete dva izvrsavanja synhronized-a metoda na istim objektu, ne mogu istovremeno
   *     da se izvrsavaju
   *  2. synhronized se sinhronizuje nad objektom
   *
   *  synhronized() blok prima neki objekat; taj objekat treba da bude deljen
   *  izmedju nasih niti, ako nije deljen izmedju nasih niti; onda ako jedna nit
   *  koristi jedan objekat, druga nit koristi drugi objekat, niti onda mogu da imaju ovu
   *  kriticnu sekciju, da se izvrsava paralelno, jer nije isti objekat nad kojim se sinhronizuju
   *
   *
   *  ovo dole se sinhronizuje nad this objektom
   *
   *  banka moze da bude objekat nad kojim se sinhronizuje
   */
  @Override
  public synchronized void transfer(int from, int to, int amount) {
    int totalBalanceAfterTransfer;

      // dok nemas novca cekas
      while (this.accounts[from] < amount) {
        try {
          // TODO wait on signal...
          this.wait();
        } catch (Exception e) {
          e.printStackTrace();
        }
      }

      System.out.println(Thread.currentThread());
      this.accounts[from] -= amount;
      this.accounts[to] += amount;
      totalBalanceAfterTransfer = this.getTotalBalance();


    // TODO signal
    this.notifyAll(); // svako ko ceka na this-u njega obavestavamo
    // to je onaj objekat nad kojim radimo sinhronizaciju

    System.out.println("Transfer from " + from + " to " + to + " : " + amount);
    System.out.println("Total Balance: " + totalBalanceAfterTransfer);

  }

  @Override
  public int count() {
    return this.accounts.length;
  }

  @Override
  public int getTotalBalance() {
    return Arrays.stream(this.accounts).sum();
  }
}
