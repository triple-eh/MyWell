package persistence;

import model.Feeling;
import model.Journal;
import model.JournalEntry;
import model.Sensation;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// Credit to https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
//       for providing the model for testing reading files
public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Journal j = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyJournal() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyJournal.json");
        try {
            Journal j = reader.read();
            assertEquals(0, j.size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralJournal() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralJournal.json");
        try {
            Journal j = reader.read();
            List<JournalEntry> entries = j.getEntries();
            assertEquals(2, entries.size());
            LocalDate date1 = LocalDate.parse("2021-10-24");
            List<Sensation> sensations1 = new ArrayList<>();
            Sensation sensation1 = new Sensation("left arm");
            sensation1.setSensationType("unpleasant");
            sensation1.setIntensity(3);
            sensation1.setNote("sore from exercise");
            sensations1.add(sensation1);
            String overallState1 = "amazing";
            List<Feeling> feelings1 = new ArrayList<>();
            checkJournalEntry(date1, overallState1, sensations1, feelings1, entries.get(0));

            LocalDate date2 = LocalDate.parse("2021-10-25");
            List<Sensation> sensations2 = new ArrayList<>();
            String overallState2 = "ok";
            List<Feeling> feelings2 = new ArrayList<>();
            Feeling feeling2 = new Feeling("happy");
            feeling2.setIntensity(4);
            feeling2.setNote("almost done with the project");
            feelings2.add(feeling2);
            checkJournalEntry(date2, overallState2, sensations2, feelings2, entries.get(1));

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
