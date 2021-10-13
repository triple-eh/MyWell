package model;

import java.time.LocalDate;
import java.util.*;

//A journal with individual entries and associated functionality
public class Journal {
    private List<JournalEntry> entries;

    //EFFECTS initializes a new journal with no entries
    public Journal() {
        this.entries = new ArrayList<>();
    }

    //getter
    public List<JournalEntry> getEntries() {
        return this.entries;
    }

    //EFFECTS returns the number of entries in current journal
    public int size() {
        return 0;
    }

    //MODIFIES this
    //EFFECTS adds a new entry to the journal
    public void addJournalEntry(JournalEntry entry) {

    }

    //REQUIRES journal has at least one entry
    //EFFECTS returns the last added entry
    public JournalEntry getLastEntry() {
        return new JournalEntry("ok");
    }

    //REQUIRES days is a non-negative integer
    //EFFECTS returns number of entries in the period of the last X days
    //        when days is 0 returns entries for today, days is 1 returns entry for today and yesterday, etc.
    public int countRecentEntries(int days) {
        return 0;
    }

    //REQUIRES days is a positive integer
    //EFFECTS returns the number of entries for each state in JournalEntry.STATES
    //        in the period of last X days specified
    public HashMap<String, Integer> countEntriesByState(int days) {
        return new HashMap<>();
    }

    //REQUIRES a valid date in the past
    //EFFECTS returns all entries made in a particular day as an array, or an empty array if
    public List<JournalEntry> getEntriesByDate(LocalDate date) {
        return new ArrayList<>();
    }
}
