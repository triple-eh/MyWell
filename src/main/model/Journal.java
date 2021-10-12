package model;

import java.util.*;

//Journal functionality
public class Journal {
    private List<JournalEntry> entries;

    //EFFECTS initializes a new journal with no entries
    public Journal() {

    }

    //REQUIRES days is a positive integer
    //EFFECTS returns number of entries in the period of the last X days
    public int sumRecentEntries(int days) {
        return 0;
    }

    //EFFECTS returns the average number of days journaling in the last 30 days
    //        multiple entries in one day count as one
    public float journalingFrequency() {
        return 0;
    }

    //REQUIRES days is a positive integer
    //EFFECTS returns the number of entries for each state in JournalEntry.STATES
    //        in the period of last X days specified
    public HashMap<String, Integer> overallStateFrequency(int days) {
        return new HashMap<>();
    }

    //REQUIRES a valid date in the past
    //EFFECTS returns all entries made in a particular day as an array, or an empty array if
    public List<JournalEntry> getEntriesByDate(Date date) {
        return new ArrayList<>();
    }
}
