import java.time.LocalDate;

public class BonusAccount implements Account{
    private final double bonus;
    private final TransactionManager transactionManager;
    private final Entries entries = new Entries();

    public BonusAccount(double bonus, TransactionManager transactionManager){
        this.bonus = bonus;
        this.transactionManager = transactionManager;
    }

    double getBonus() {
        return bonus;
    }

    @Override
    public double balanceOn(LocalDate date) {
        double balance = 0;
        for(Entry entry : entries.betweenDates(null, date)) {
            balance += entry.amount;
        }
        return balance;
    }

    @Override
    public void addEntry(Entry entry) {
        entries.addEntry(entry);
    }

//    @Override
//    public double balanceOn(LocalDate date) {
//        double result = 0;
//        for(var entry : entries.betweenDates(null, date)) {
//            result += entry.getAmount();
//        }
//        return result;
//    }
//
//    @Override
//    public void addEntry(Entry entry) {
//        entries.addEntry(entry);
//    }
}
