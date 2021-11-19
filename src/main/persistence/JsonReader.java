package persistence;

import model.Feeling;
import model.Journal;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import model.JournalEntry;
import model.Sensation;
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
    public Journal readJournal() throws IOException {
        return parseJournal(read());
    }

    // EFFECTS: reads json object from file and returns it;
    // throws IOException if an error occurs reading data from file
    public JSONObject read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return jsonObject;
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(contentBuilder::append);
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses journal from JSON object and returns it
    private Journal parseJournal(JSONObject jsonObject) {
        Journal j = new Journal();
        addEntries(j, jsonObject);
        return j;
    }

    // MODIFIES: j
    // EFFECTS: parses entries from JSON object and adds them to journal
    private void addEntries(Journal j, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("entries");
        for (Object json : jsonArray) {
            JSONObject nextEntry = (JSONObject) json;
            addEntry(j, nextEntry);
        }
    }

    // MODIFIES: j
    // EFFECTS: parses and entry from JSON object and adds it to journal
    private void addEntry(Journal j, JSONObject jsonObject) {
        LocalDate date = LocalDate.parse(jsonObject.getString("date"));
        String state = jsonObject.getString("overallState");
        List<Sensation> sensations = new ArrayList<>();
        for (int i = 0; i < jsonObject.getJSONArray("sensations").length(); i++) {
            JSONObject json = jsonObject.getJSONArray("sensations").getJSONObject(i);
            sensations.add(getSensationFromJson(json));
        }
        List<Feeling> feelings = new ArrayList<>();
        for (int i = 0; i < jsonObject.getJSONArray("feelings").length(); i++) {
            JSONObject json = jsonObject.getJSONArray("feelings").getJSONObject(i);
            feelings.add(getFeelingFromJson(json));
        }
        JournalEntry entry = new JournalEntry(date, state, sensations, feelings);
        j.addJournalEntry(entry);
    }

    // REQUIRES valid JSON object with sensation data
    // EFFECTS: parses a sensation from JSONObject and returns it
    private Sensation getSensationFromJson(JSONObject json) {
        String bodyPart = json.getString("bodyPart");
        String sensationType = json.getString("sensationType");
        int intensity = json.getInt("intensity");
        String note = json.getString("note");
        Sensation sensation = new Sensation(bodyPart);
        sensation.setSensationType(sensationType);
        sensation.setIntensity(intensity);
        sensation.setNote(note);
        return sensation;
    }

    // REQUIRES valid JSON object with feeling data
    // EFFECTS: parses a feeling from JSONObject and returns it
    private Feeling getFeelingFromJson(JSONObject json) {
        String feelingName = json.getString("feelingName");
        int intensity = json.getInt("intensity");
        String note = json.getString("note");
        Feeling feeling = new Feeling(feelingName);
        feeling.setIntensity(intensity);
        feeling.setNote(note);
        return feeling;
    }
}