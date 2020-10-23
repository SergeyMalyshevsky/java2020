import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

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
        return transactionsByAccountList.stream()
                .sorted(Comparator.comparing(Transaction::getAmount))
                .limit(10)
                .collect(Collectors.toList());
    }

    public double overallBalanceOfAccounts(List<Account> accounts) {
        return 0d;
    }

    public Set uniqueKeysOf(List accounts, KeyExtractor extractor) {
        return null;
    }

    public List accountsRangeFrom(List accounts, Account minAccount, Comparator comparator) {
        return null;
    }

    Optional<Entry> maxExpenseAmountEntryWithinInterval(List<Account> accounts, LocalDate from, LocalDate to) {
        return accounts.stream()
                .map(account -> (DebitCard) account)
                .map(account -> account.history(from, to))
                .flatMap(Collection::stream)
                .filter(entry -> entry.getAmount() < 0)
                .min(Comparator.comparing(Entry::getAmount));
    }

}
