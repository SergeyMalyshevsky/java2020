import java.time.LocalDate;

public interface Account {
    public double balanceOn(LocalDate date);

    void addEntry(Entry entry);
}
