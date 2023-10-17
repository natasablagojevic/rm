public class Sinhronizacija {
  static final int ACCOUNTS = 5;
  static final int STARTING_BALANCE = 1000000;


  /*
   * Banka obezbedjuje cuvanje novca na racunima
   * i svakog korisnika simuliramo zasebnom niti i
   * ta nit ce da radi transver sa svog racuna na drugi
   * racun.
   */
  public static void main(String[] args) {
    //Bank bank = new Bank(ACCOUNTS, STARTING_BALANCE);

    IBank bank = new LockBank(ACCOUNTS, STARTING_BALANCE);

    for (int i = 0; i < ACCOUNTS; i++) {
      TransferRunnable transfer = new TransferRunnable(bank, i, 10);
      Thread t = new Thread(transfer);
      t.start();
    }
  }
}
