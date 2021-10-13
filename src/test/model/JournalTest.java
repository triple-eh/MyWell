package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class JournalTest {
    Journal j1;
    JournalEntry entry1, entry2, entry3, entry4;
    LocalDate twoDaysAgo, fourDaysAgo, fiveDaysAgo, tenDaysAgo;
    List<JournalEntry> entries = new ArrayList<>();

    @BeforeEach
    public void setup() {
        j1 = new Journal();
        this.twoDaysAgo = LocalDate.now().minusDays(2);
        this.fourDaysAgo = LocalDate.now().minusDays(4);
        this.tenDaysAgo = LocalDate.now().minusDays(10);
        this.entry1 = new JournalEntry(twoDaysAgo,"bad");
        this.entry2 = new JournalEntry(twoDaysAgo,"bad");
        this.entry3 = new JournalEntry(fourDaysAgo,"terrible");
        this.entry4 = new JournalEntry(tenDaysAgo,"good");
        entries.add(entry1);
        entries.add(entry3);
        entries.add(entry2);
        entries.add(entry4);
    }

    @Test
    public void journalTest() {
        Assertions.assertEquals(0,j1.size());
    }

    @Test
    public void addJournalEntryTest() {
        Assertions.assertEquals(0,j1.size());
        for (int i = 0; i < entries.size(); i++) {
            j1.addJournalEntry(entries.get(i));
            Assertions.assertEquals(i+1, j1.size());
            Assertions.assertEquals(entries.get(i),j1.getLastEntry());
        }
    }

    @Test
    public void countRecentEntriesTest() {
        for (int i = 0; i < entries.size(); i++) {
            j1.addJournalEntry(entries.get(i));
        }
        Assertions.assertEquals(0,j1.countRecentEntries(0));
        Assertions.assertEquals(2,j1.countRecentEntries(2));
        Assertions.assertEquals(3,j1.countRecentEntries(7));
    }

    @Test
    public void countEntriesByStateTest() {
        for (int i = 0; i < entries.size(); i++) {
            j1.addJournalEntry(entries.get(i));
        }
        HashMap<String, Integer> countEntriesByState30 = j1.countEntriesForAllStates(30);
        Assertions.assertEquals(1,countEntriesByState30.get("terrible"));
        Assertions.assertEquals(2,countEntriesByState30.get("bad"));
        Assertions.assertEquals(0,countEntriesByState30.get("ok"));
        Assertions.assertEquals(1,countEntriesByState30.get("good"));
        Assertions.assertEquals(0,countEntriesByState30.get("amazing"));
        HashMap<String, Integer> countEntriesByState1 = j1.countEntriesForAllStates(1);
        Assertions.assertEquals(0,countEntriesByState1.get("terrible"));
        Assertions.assertEquals(0,countEntriesByState1.get("bad"));
        Assertions.assertEquals(0,countEntriesByState1.get("ok"));
        Assertions.assertEquals(0,countEntriesByState1.get("good"));
        Assertions.assertEquals(0,countEntriesByState1.get("amazing"));
    }

    @Test
    public void getEntriesByDateTest() {
        for (int i = 0; i < entries.size(); i++) {
            j1.addJournalEntry(entries.get(i));
        }
        LocalDate fiveDaysAgo = LocalDate.now().minusDays(5);
        LocalDate Oct082021 = LocalDate.of(2021,9,8);
        LocalDate Oct072021 = LocalDate.of(2021,9,7);
        List<JournalEntry> entriesTwoDaysAgo = j1.getEntriesByDate(twoDaysAgo);
        Assertions.assertTrue(entriesTwoDaysAgo.contains(entry1));
        Assertions.assertTrue(entriesTwoDaysAgo.contains(entry2));
        Assertions.assertEquals(2, entriesTwoDaysAgo.size());
        List<JournalEntry> entriesFourDaysAgo = j1.getEntriesByDate(fourDaysAgo);
        Assertions.assertTrue(entriesFourDaysAgo.contains(entry3));
        Assertions.assertEquals(1,entriesFourDaysAgo.size());
        List<JournalEntry> entriesFiveDaysAgo = j1.getEntriesByDate(fiveDaysAgo);
        Assertions.assertEquals(0,entriesFiveDaysAgo.size());


    }

}