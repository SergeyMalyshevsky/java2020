import java.time.LocalDateTime;

/**
 * The record of allocating the amount to the account
 * Amount can be either positive or negative depending on originator or beneficiary
 */
public class Entry {
    final Account account;
    final Transaction transaction;
    final double amount;
    final LocalDateTime time;

    public Entry(Account account, Transaction transaction, double amount, LocalDateTime time) {
        this.account = account;
        this.transaction = transaction;
        this.amount = amount;
        this.time = time;
    }
}
