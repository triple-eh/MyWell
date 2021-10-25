package persistence;

import model.Journal;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.stream.Stream;

import model.JournalEntry;
import org.json.*;

// Represents a reader that reads workroom from JSON data stored in file
// Credit to https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
//       for providing the model for reading JSON files
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads journal from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Journal read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseJournal(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines( Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses journal from JSON object and returns it
    private Journal parseJournal(JSONObject jsonObject) {
        //Could give the journal a name
        //String name = jsonObject.getString("name");
        Journal j = new Journal();
        addEntries(j, jsonObject);
        return j;
    }

    // MODIFIES: j
    // EFFECTS: parses thingies from JSON object and adds them to workroom
    private void addEntries(Journal j, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("entries");
        for (Object json : jsonArray) {
            JSONObject nextEntry = (JSONObject) json;
            addEntry(j, nextEntry);
        }
    }

    // MODIFIES: j
    // EFFECTS: parses thingy from JSON object and adds it to workroom
    private void addEntry(Journal j, JSONObject jsonObject) {
        LocalDate date = LocalDate.parse(jsonObject.getString("date"));
        String state = jsonObject.getString("overallState");
        JournalEntry entry = new JournalEntry(date, state);
        j.addJournalEntry(entry);
    }
}