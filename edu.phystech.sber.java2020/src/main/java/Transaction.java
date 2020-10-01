import java.time.LocalDateTime;

public class Transaction {
    private final long id;
    private final double amount;
    private final Account originator;
    private final Account beneficiary;
    private boolean executed;
    private boolean rolledBack;


    public Transaction(long id, double amount, Account originator, Account beneficiary) {
        this.id = id;
        this.amount = amount;
        this.originator = originator;
        this.beneficiary = beneficiary;
        this.executed = false;
        this.rolledBack = false;
    }
    /**
     * Adding entries to both accounts
     * @throws IllegalStateException when was already executed
     */
    public Transaction execute() {
        if (executed) {
            throw new IllegalStateException("transaction is already executed");
        }
        executed = true;
        return this;
    }

    /**
     * Removes all entries of current transaction from originator and beneficiary
     * @throws IllegalStateException when was already rolled back
     */
    public Transaction rollback() {
        if (rolledBack) {
            throw new IllegalStateException("transaction is already rolled back");
        }
        executed = false;
        rolledBack = true;
        return this;
    }

    public Account getBeneficiary() {
        return beneficiary;
    }

    public Double getAmount() {
        return amount;
    }

    public boolean isExecuted() {
        if (executed) {
            return true;
        }
        return false;
    }

    public boolean isRolledBack() {
        if (rolledBack) {
            return true;
        }
        return false;
    }
}

