package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class JournalEntryTest {

    @Test
    public void journalEntryTestWithDefaultDate() {
        LocalDate today = LocalDate.now();
        JournalEntry entryA = new JournalEntry("terrible");
        Assertions.assertEquals("terrible",entryA.getOverallState());
        Assertions.assertEquals(today, entryA.getDate());
        Assertions.assertEquals(0, entryA.getFeelings().size());
        Assertions.assertEquals(0, entryA.getSensations().size());
        JournalEntry entryB = new JournalEntry("bad");
        Assertions.assertEquals("bad",entryB.getOverallState());
        Assertions.assertEquals(0, entryB.getFeelings().size());
        Assertions.assertEquals(0, entryB.getSensations().size());
    }

    @Test
    public void journalEntryTestWithSetDate() {
        LocalDate date1 = LocalDate.of(2021,10,1);
        LocalDate date2 = LocalDate.of(2021,10,2);
        JournalEntry entryA = new JournalEntry(date1,"good");
        Assertions.assertEquals(date1, entryA.getDate());
        JournalEntry entryB = new JournalEntry(date2,"amazing");
        Assertions.assertEquals(date2, entryB.getDate());
    }

    @Test
    public void addSensationTest() {
        JournalEntry entryA = new JournalEntry("good");
        Assertions.assertEquals(0,entryA.getSensations().size());
        Sensation sensation1 = new Sensation("left arm");
        Sensation sensation2 = new Sensation("right arm");
        entryA.addSensation(sensation1);
        Assertions.assertEquals(1,entryA.getSensations().size());
        entryA.addSensation(sensation2);
        Assertions.assertEquals(2,entryA.getSensations().size());
        Assertions.assertEquals(sensation1, entryA.getSensations().get(0));
        Assertions.assertEquals(sensation2, entryA.getSensations().get(1));
        Assertions.assertFalse(entryA.addSensation(sensation1));
    }

    @Test
    public void addFeelingTest() {
        JournalEntry entryA = new JournalEntry("good");
        Assertions.assertEquals(0,entryA.getFeelings().size());
        Feeling feeling1 = new Feeling("elated");
        Feeling feeling2 = new Feeling("anxious");
        entryA.addFeeling(feeling1);
        Assertions.assertEquals(1,entryA.getFeelings().size());
        entryA.addFeeling(feeling2);
        Assertions.assertEquals(2,entryA.getFeelings().size());
        Assertions.assertEquals(feeling1, entryA.getFeelings().get(0));
        Assertions.assertEquals(feeling2, entryA.getFeelings().get(1));
        Assertions.assertFalse(entryA.addFeeling(feeling1));
    }
}
