import java.util.Arrays;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockBank implements IBank{
  private final int[] accounts;
  private Lock lock;
  private final Condition insufficentFunds;

  LockBank(int accountNum, int initialBalance) {
    this.accounts = new int[accountNum];
    Arrays.fill(this.accounts, initialBalance);
    this.lock = new ReentrantLock();
    this.insufficentFunds = this.lock.newCondition();
  }

  @Override
  public void transfer(int from, int to, int amount) {
    int totalBalanceAfterTransfer;

    this.lock.lock();
    try {
      // dok nemas novca cekas
      while (this.accounts[from] < amount) {
        try {
          this.insufficentFunds.await();
        } catch (Exception e) {
          e.printStackTrace();
        }
      }

      System.out.println(Thread.currentThread());
      this.accounts[from] -= amount;
      this.accounts[to] += amount;
      totalBalanceAfterTransfer = this.getTotalBalance();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      this.lock.unlock();
    }

    System.out.println("Transfer from " + from + " to " + to + " : " + amount);
    System.out.println("Total Balance: " + totalBalanceAfterTransfer);

    // kad uradimo transfer saljemo signal

    // svima koji cekaju dobice signal
    // atomicno ce da hvataju katanac i da nastave dalje
    this.insufficentFunds.signalAll();

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
