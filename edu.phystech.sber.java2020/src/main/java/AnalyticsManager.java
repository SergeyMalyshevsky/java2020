import java.util.*;

public class AnalyticsManager {
    private final TransactionManager transactionManager;

    public AnalyticsManager(TransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    public Account mostFrequentBeneficiaryOfAccount(Account account) {
        Account mostFrequentBeneficiary = null;
        int mostFrequentBeneficiaryCount = 0;

        HashMap<Account, Integer> beneficiaryFrequency = new HashMap<>();
        Collection<Transaction> transactionsByAccount = transactionManager.findAllTransactionsByAccount(account);
        for (Transaction transaction : transactionsByAccount) {
            if (!beneficiaryFrequency.containsKey(transaction.getBeneficiary())) {
                beneficiaryFrequency.put(account, 1);
            } else {
                Integer count = beneficiaryFrequency.get(account);
                count++;
                beneficiaryFrequency.put(account, count);
                if (count > mostFrequentBeneficiaryCount) {
                    mostFrequentBeneficiary = account;
                    mostFrequentBeneficiaryCount = count;
                }
            }
        }

        return mostFrequentBeneficiary;
    }

    public Collection<Transaction> topTenExpensivePurchases(Account account) {
        Collection<Transaction> transactionsByAccount = transactionManager.findAllTransactionsByAccount(account);
        ArrayList<Transaction> transactionsByAccountList = new ArrayList<>(transactionsByAccount);
        transactionsByAccountList.sort((t1, t2) -> t1.getAmount().compareTo(t2.getAmount()));
        ArrayList<Transaction> topTen = new ArrayList<>(transactionsByAccountList.subList(0, 9));
        return topTen;
    }
}
