import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Collection of entries for the debitCard. Use it to save and get history of payments
 */
public class Entries {
    private ArrayList<Entry> entries;

    public Entries() {
        this.entries = new ArrayList<>();
    }

    void addEntry(Entry entry) {
        entries.add(entry);
    }

    Collection<Entry> from(LocalDate date) {
        ArrayList<Entry> entriesFromDate = new ArrayList<>();
        for (Entry entry: entries) {
            if (date.atStartOfDay().isBefore(entry.time)) {
                entriesFromDate.add(entry);
            }
        }
        return entriesFromDate;
    }

    Collection<Entry> betweenDates(LocalDate from, LocalDate to) {
        ArrayList<Entry> entriesBetweenDates = new ArrayList<>();
        for (Entry entry: entries) {
            if (from.atStartOfDay().isBefore(entry.time) && to.atStartOfDay().isAfter(entry.time)) {
                entriesBetweenDates.add(entry);
            }
        }
        return entriesBetweenDates;
    }

    Entry last() {
        Entry last = entries.get(entries.size() - 1);
        return last;

    }
}

