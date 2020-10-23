import java.time.LocalDateTime;

/**
 * The record of allocating the amount to the debitCard
 * Amount can be either positive or negative depending on originator or beneficiary
 */
public class Entry {
    final DebitCard debitCard;
    final Transaction transaction;
    final double amount;
    final LocalDateTime time;

    public Entry(DebitCard debitCard, Transaction transaction, double amount, LocalDateTime time) {
        this.debitCard = debitCard;
        this.transaction = transaction;
        this.amount = amount;
        this.time = time;
    }

    public double getAmount() {
        return this.amount;
    }

}
