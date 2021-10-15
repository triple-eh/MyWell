package model;

import java.time.LocalDate;
import java.util.*;

import static java.time.temporal.ChronoUnit.DAYS;

//A journal with individual entries and associated functionality
public class Journal {
    private List<JournalEntry> entries;

    //getter
    public List<JournalEntry> getEntries() {
        return this.entries;
    }

    //EFFECTS initializes a new journal with no entries
    public Journal() {
        this.entries = new ArrayList<>();
    }

    //EFFECTS returns the number of entries in current journal
    public int size() {
        return entries.size();
    }

    //MODIFIES this
    //EFFECTS adds a new entry to the journal
    public void addJournalEntry(JournalEntry entry) {
        entries.add(entry);
    }

    //REQUIRES journal has at least one entry
    //EFFECTS returns the last added entry
    public JournalEntry getLastEntry() {
        return entries.get(entries.size() - 1);
    }

    //REQUIRES days is a non-negative integer
    //EFFECTS returns number of entries in the period of the last X days
    //        when days is 0 returns entries for today, days is 1 returns entry for today and yesterday, etc.
    public int countRecentEntries(int days) {
        int countRecentEntries = 0;
        LocalDate today = LocalDate.now();
        if (this.entries.size() != 0) {
            for (JournalEntry entry : entries) {
                long daysBetween = DAYS.between(entry.getDate(), today);
                if (days >= daysBetween) {
                    countRecentEntries++;
                }
            }
        }
        return countRecentEntries;
    }

    //REQUIRES days is a positive integer
    //EFFECTS returns the number of entries for each state in JournalEntry.STATES
    //        in the period of last X days specified
    public HashMap<String, Integer> countEntriesForAllStates(int days) {
        HashMap<String, Integer> result = new HashMap<>();
        for (String state : JournalEntry.STATES) {
            result.put(state, countEntriesByState(state,days));
        }
        return result;
    }

    //REQUIRES state be a valid overall state in JournalEntry.STATES
    //EFFECTS returns the number of entries for a given state in the period of last X days specified
    private int countEntriesByState(String state, int days) {
        int count = 0;
        LocalDate today = LocalDate.now();
        if (this.entries.size() != 0) {
            for (JournalEntry entry : entries) {
                long daysBetween = DAYS.between(entry.getDate(), today);
                if (days >= daysBetween && entry.getOverallState().equals(state)) {
                    count++;
                }
            }
        }
        return count;
    }

    //REQUIRES a valid date in the past
    //EFFECTS returns all entries made in a particular day as an array, or an empty array if no entries found
    public List<JournalEntry> getEntriesByDate(LocalDate date) {
        List<JournalEntry> result = new ArrayList<>();
        if (this.entries.size() != 0) {
            for (JournalEntry entry: entries) {
                if (DAYS.between(date,entry.getDate()) == 0) {
                    result.add(entry);
                }
            }
        }
        return result;
    }
}
