package persistence;

import model.Feeling;
import model.JournalEntry;
import model.Sensation;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class JsonTest {
    protected void checkJournalEntry(LocalDate date, String overallState, List<Sensation> sensations,
                                     List<Feeling> feelings, JournalEntry entry) {
        assertEquals(date, entry.getDate());
        assertEquals(overallState, entry.getOverallState());
        assertEquals(sensations.size(),entry.getSensations().size());
        for (Sensation s : sensations) {
            assertTrue(entry.getSensations().contains(s));
        }
        assertEquals(feelings.size(),entry.getFeelings().size());
        for (Feeling f : feelings) {
            assertTrue(entry.getFeelings().contains(f));
        }
    }
}
