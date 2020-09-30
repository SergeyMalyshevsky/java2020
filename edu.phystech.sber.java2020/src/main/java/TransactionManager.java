import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

/**
 * Manages all transactions within the application
 */
public class TransactionManager {
    private long transactionNextId;
    private HashMap<Account, ArrayList<Transaction>> transactions;

    public TransactionManager(){
        this.transactionNextId = 1;
        this.transactions = new HashMap<>();
    }

    /**
     * Creates and stores transactions
     *
     * @param amount
     * @param originator
     * @param beneficiary
     * @return created Transaction
     */
    public Transaction createTransaction(double amount,
                                         Account originator,
                                         Account beneficiary) {

        Transaction transaction = new Transaction(transactionNextId, amount, originator, beneficiary);

        if (!transactions.containsKey(originator)) {
            transactions.put(originator, new ArrayList<>());
        } else {
            transactions.get(originator).add(transaction);
        }

        transactionNextId++;
        return transaction;
    }

    public Collection<Transaction> findAllTransactionsByAccount(Account account) {
        Collection<Transaction> transactionsByAccount = transactions.get(account);
        return transactionsByAccount;
    }


    public void rollbackTransaction(Transaction transaction) {
        transaction.rollback();
    }

    public void executeTransaction(Transaction transaction) {
        transaction.execute();
    }
}
