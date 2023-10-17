import java.util.Arrays;

public class Bank implements IBank{

  private final int[] accounts;

  Bank(int accountNum, int initialBalance) {
    this.accounts = new int[accountNum];
    Arrays.fill(this.accounts, initialBalance);
  }

  @Override
  public void transfer(int from, int to, int amount) {
    if (this.accounts[from] < amount)
      return;

    System.out.println(Thread.currentThread());
    this.accounts[from] -= amount;
    this.accounts[to] += amount;

//    System.out.println("Transfer from %2d to %2d: %4d\n", from, to, amount);
    System.out.println("Transfer from " + from + " to " + to + " : " + amount);
    System.out.println("Total Balance: " + this.getTotalBalance());
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
