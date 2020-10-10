import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;

public class DebitCard implements Account{
    private final long id;
    private final TransactionManager transactionManager;
    private final BonusAccount bonusAccount;
    private final Entries entries;

    public DebitCard(long id, TransactionManager transactionManager) {
        this.id = id;
        this.transactionManager = transactionManager;
        this.bonusAccount = null;
        this.entries = new Entries();
    }

    public DebitCard(long id, TransactionManager transactionManager, BonusAccount bonusAccount) {
        this.id = id;
        this.bonusAccount = bonusAccount;
        this.transactionManager = transactionManager;
        this.entries = new Entries();
    }

    /**
     * Withdraws money from debitCard. <b>Should use TransactionManager to manage transactions</b>
     *
     * @param amount amount of money to withdraw
     * @return true
     * if amount &gt 0 and (currentBalance - amount) &ge 0,
     * otherwise returns false
     */
    public boolean withdraw(double amount, DebitCard beneficiary) {
        LocalDateTime currentTime = LocalDateTime.now().plusDays(1);
        if (amount > 0 && (balanceOn(currentTime.toLocalDate()) - amount) > 0) {
            Transaction transaction = transactionManager.createTransaction(amount, this, beneficiary);
            entries.addEntry(new Entry(beneficiary, transaction, -1 * amount, LocalDateTime.now()));
            if (beneficiary != null) {
                beneficiary.add(amount, beneficiary);
            }
            if (bonusAccount != null && beneficiary != null) {
                Transaction bonusTransaction = transactionManager.createTransaction(
                        amount + amount * bonusAccount.getBonus(), this, beneficiary);
                transactionManager.executeTransaction(bonusTransaction);
            } else {
                transactionManager.executeTransaction(transaction);
            }
            return true;
        }
        return false;
    }

    /**
     * Withdraws cash money from debitCard. <b>Should use TransactionManager to manage transactions</b>
     *
     * @param amount amount of money to withdraw
     * @return true
     * if amount &gt 0 and (currentBalance - amount) &ge 0,
     * otherwise returns false
     */
    public boolean withdrawCash(double amount) {
        return withdraw(amount, null);
    }

    /**
     * Adds cash money to debitCard. <b>Should use TransactionManager to manage transactions</b>
     *
     * @param amount amount of money to add
     * @return true
     * if amount &gt 0,
     * otherwise returns false
     */
    public boolean addCash(double amount) {
        return add(amount, null);
    }

    /**
     * Adds money to debitCard. <b>Should use TransactionManager to manage transactions</b>
     *
     * @param amount amount of money to add
     * @return true
     * if amount &gt 0,
     * otherwise returns false
     */
    public boolean add(double amount, DebitCard originator) {
        if (amount > 0) {
            Transaction transaction = transactionManager.createTransaction(amount, null, this);
            entries.addEntry(new Entry(originator, transaction, amount, LocalDateTime.now()));
            transactionManager.executeTransaction(transaction);
            return true;
        }
        return false;
    }

    public Collection<Entry> history(LocalDate from, LocalDate to) {
        return entries.betweenDates(from, to);
    }

    /**
     * Calculates balance on the accounting entries basis
     * @param date
     * @return balance
     */
    public double balanceOn(LocalDate date) {
        double balance = 0.;
        LocalDate unixEpoch = LocalDate.EPOCH;
        for (Entry entry : entries.betweenDates(unixEpoch, date)) {
            if (entry.transaction.isExecuted() && !entry.transaction.isRolledBack()) {
                balance += entry.amount;
            }
        }
        return balance;
    }

    @Override
    public void addEntry(Entry entry) {

    }

    /**
     * Finds the last transaction of the debitCard and rollbacks it
     */
    public void rollbackLastTransaction() {
        TransactionManager transactionManager = new TransactionManager();
        Entry lastEntry = entries.last();
        Transaction transaction = lastEntry.transaction;
        transactionManager.rollbackTransaction(transaction);
    }
}
