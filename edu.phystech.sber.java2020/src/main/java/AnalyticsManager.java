import java.util.*;

public class AnalyticsManager {
    private final TransactionManager transactionManager;

    public AnalyticsManager(TransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    public DebitCard mostFrequentBeneficiaryOfAccount(DebitCard debitCard) {
        DebitCard mostFrequentBeneficiary = null;
        int mostFrequentBeneficiaryCount = 0;

        HashMap<DebitCard, Integer> beneficiaryFrequency = new HashMap<>();
        Collection<Transaction> transactionsByAccount = transactionManager.findAllTransactionsByAccount(debitCard);
        for (Transaction transaction : transactionsByAccount) {
            if (!beneficiaryFrequency.containsKey(transaction.getBeneficiary())) {
                beneficiaryFrequency.put(debitCard, 1);
            } else {
                Integer count = beneficiaryFrequency.get(debitCard);
                count++;
                beneficiaryFrequency.put(debitCard, count);
                if (count > mostFrequentBeneficiaryCount) {
                    mostFrequentBeneficiary = debitCard;
                    mostFrequentBeneficiaryCount = count;
                }
            }
        }

        return mostFrequentBeneficiary;
    }

    public Collection<Transaction> topTenExpensivePurchases(DebitCard debitCard) {
        Collection<Transaction> transactionsByAccount = transactionManager.findAllTransactionsByAccount(debitCard);
        ArrayList<Transaction> transactionsByAccountList = new ArrayList<>(transactionsByAccount);
        transactionsByAccountList.sort((t1, t2) -> t1.getAmount().compareTo(t2.getAmount()));
        ArrayList<Transaction> topTen = new ArrayList<>(transactionsByAccountList.subList(0, 9));
        return topTen;
    }
}
