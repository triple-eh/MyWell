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
//       for providing the model for testing writing files
public class JsonWriterTest extends JsonTest {
    @Test
    void testWriterInvalidFile() {
        try {
            Journal j = new Journal();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyJournal() {
        try {
            Journal j = new Journal();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyJournal.json");
            writer.open();
            writer.write(j);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyJournal.json");
            j = reader.readJournal();
            assertEquals(0, j.size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralJournal() {
        try {
            Journal j = new Journal();
            LocalDate date1 = LocalDate.parse("2021-10-23");
            List<Sensation> sensations1 = new ArrayList<>();
            Sensation sensation1 = new Sensation("neck");
            sensation1.setSensationType("neutral");
            sensation1.setIntensity(2);
            sensation1.setNote("weird tingling");
            sensations1.add(sensation1);
            String overallState1 = "good";
            List<Feeling> feelings1 = new ArrayList<>();
            j.addJournalEntry(new JournalEntry(date1, overallState1, sensations1, feelings1));

            LocalDate date2 = LocalDate.parse("2021-10-24");
            List<Sensation> sensations2 = new ArrayList<>();
            String overallState2 = "bad";
            List<Feeling> feelings2 = new ArrayList<>();
            Feeling feeling2 = new Feeling("unhappy");
            feeling2.setIntensity(4);
            feeling2.setNote("feeling super tired");
            feelings2.add(feeling2);
            j.addJournalEntry(new JournalEntry(date2, overallState2, sensations2, feelings2));

            JsonWriter writer = new JsonWriter("./data/testWriterGeneralJournal.json");
            writer.open();
            writer.write(j);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralJournal.json");
            j = reader.readJournal();
            List<JournalEntry> entries = j.getEntries();
            assertEquals(2, entries.size());
            checkJournalEntry(date1, overallState1, sensations1, feelings1, entries.get(0));
            checkJournalEntry(date2, overallState2, sensations2, feelings2, entries.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
