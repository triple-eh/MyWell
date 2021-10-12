package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//A single entry in the journal
public class JournalEntry {
    private LocalDate date;
    private String overallState;
    private List<Sensation> sensations;
    private List<Feeling> feelings;
    public static final List<String> STATES = new ArrayList<>(Arrays.asList("terrible",
            "bad", "ok", "good", "amazing"));

    //REQUIRES overallState be one of STATES
    //EFFECTS creates a new journal entry with today's date and specified overall state
    public JournalEntry(String overallState) {

    }

    //REQUIRES a valid date not greater than today's date and overallState be one of STATES
    //EFFECTS creates a new journal entry with specified date and specified overall state
    public JournalEntry(LocalDate date, String overallState) {

    }

    //getters
    public LocalDate getDate() {

        return this.date;
    }

    public String getOverallState() {

        return this.overallState;
    }

    public List<Sensation> getSensations() {

        return new ArrayList<>();

    }

    public List<Feeling> getFeelings() {

        return new ArrayList<>();

    }

    //EFFECTS adds sensation to the list of sensations if associated body part
    //        doesn't already have a sensation in the list, and returns true
    //        otherwise returns false
    public boolean addSensation(Sensation sensation) {
        return false;
    }

    //EFFECTS adds feeling to the list of feelings if not already in the list, and returns true
    //        otherwise returns false
    public boolean addFeeling(Feeling feeling) {
        return false;
    }

    //maybe have an update method?
}
