import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;

public class Account {
    private final long id;
    private final TransactionManager transactionManager;
    private final Entries entries;

    public Account(long id, TransactionManager transactionManager) {
        this.id = id;
        this.transactionManager = transactionManager;
        this.entries = new Entries();
    }

    /**
     * Withdraws money from account. <b>Should use TransactionManager to manage transactions</b>
     *
     * @param amount amount of money to withdraw
     * @return true
     * if amount &gt 0 and (currentBalance - amount) &ge 0,
     * otherwise returns false
     */
    public boolean withdraw(double amount, Account beneficiary) {
        LocalDateTime currentTime = LocalDateTime.now().plusDays(1);
        if (amount > 0 && (balanceOn(currentTime.toLocalDate()) - amount) > 0) {
            Transaction transaction = transactionManager.createTransaction(amount, this, beneficiary);
            entries.addEntry(new Entry(beneficiary, transaction, -1 * amount, LocalDateTime.now()));
            if (beneficiary != null) {
                beneficiary.add(amount, beneficiary);
            }
            transactionManager.executeTransaction(transaction);
            return true;
        }
        return false;
    }

    /**
     * Withdraws cash money from account. <b>Should use TransactionManager to manage transactions</b>
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
     * Adds cash money to account. <b>Should use TransactionManager to manage transactions</b>
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
     * Adds money to account. <b>Should use TransactionManager to manage transactions</b>
     *
     * @param amount amount of money to add
     * @return true
     * if amount &gt 0,
     * otherwise returns false
     */
    public boolean add(double amount, Account originator) {
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

    /**
     * Finds the last transaction of the account and rollbacks it
     */
    public void rollbackLastTransaction() {
        TransactionManager transactionManager = new TransactionManager();
        Entry lastEntry = entries.last();
        Transaction transaction = lastEntry.transaction;
        transactionManager.rollbackTransaction(transaction);
    }
}
