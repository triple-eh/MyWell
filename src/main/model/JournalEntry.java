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

    //REQUIRES a valid data not greater than today's date and overallState be one of STATES
    //EFFECTS creates a new journal entry with specified date, overall state, sensations, and feelings
    public JournalEntry(LocalDate date, String overallState, List<Sensation> sensations, List<Feeling> feelings) {
        this.date = date;
        this.overallState = overallState;
        this.sensations = sensations;
        this.feelings = feelings;
        EventLog.getInstance().logEvent(
                new Event("A new journal entry " + this + " as of " + this.date.toString()
                        + " was initiated with an overall state \"" + overallState + "\"\n\n"));
    }

    //REQUIRES a valid date not greater than today's date and overallState be one of STATES
    //EFFECTS creates a new journal entry with specified date and specified overall state
    //        and initializes empty sensations and feelings
    public JournalEntry(LocalDate date, String overallState) {
        this(date, overallState, new ArrayList<>(), new ArrayList<>());
    }

    //REQUIRES overallState be one of STATES
    //EFFECTS creates a new journal entry with today's date and specified overall state
    //        and initializes empty sensations and feelings
    public JournalEntry(String overallState) {
        this(LocalDate.now(),overallState);
    }


    //getters
    public LocalDate getDate() {
        return this.date;
    }

    public String getOverallState() {
        return this.overallState;
    }

    public List<Sensation> getSensations() {
        return this.sensations;
    }

    public List<Feeling> getFeelings() {
        return this.feelings;
    }

    //setters
    public void setOverallState(String state) {
        this.overallState = state;
    }

    //EFFECTS adds sensation to the list of sensations if associated body part
    //        doesn't already have a sensation in the list, and returns true
    //        otherwise returns false
    public boolean addSensation(Sensation sensation) {
        if (!sensationWithBodyPartAlreadyPresent(sensation.getBodyPart())) {
            sensations.add(sensation);
            EventLog.getInstance().logEvent(
                    new Event("A new sensation in "
                            + sensation.getBodyPart() + " added to " + this + "\n\n"));
            return true;
        }
        return false;
    }

    //EFFECTS returns true if associated body part already have a sensation in the list
    private boolean sensationWithBodyPartAlreadyPresent(String bodyPart) {
        boolean sensationWithBodyPartAlreadyPresent = false;
        if (this.sensations.size() != 0) {
            for (Sensation s : sensations) {
                if (s.getBodyPart().equals(bodyPart)) {
                    sensationWithBodyPartAlreadyPresent = true;
                    break;
                }
            }
        }
        return sensationWithBodyPartAlreadyPresent;
    }

    //EFFECTS adds feeling to the list of feelings if not already in the list, and returns true
    //        otherwise returns false
    public boolean addFeeling(Feeling feeling) {
        if (!feelingAlreadyPresent(feeling.getFeelingName())) {
            feelings.add(feeling);
            EventLog.getInstance().logEvent(
                    new Event("A new feeling: " + feeling.getFeelingName()
                            + " added to " + this + "\n\n"));
            return true;
        }
        return false;
    }

    //EFFECTS returns true if feeling is already in the list of feelings
    private boolean feelingAlreadyPresent(String feelingName) {
        boolean feelingAlreadyPresent = false;
        if (this.feelings.size() != 0) {
            for (Feeling f : feelings) {
                if (f.getFeelingName().equals(feelingName)) {
                    feelingAlreadyPresent = true;
                    break;
                }
            }
        }
        return feelingAlreadyPresent;
    }

    @Override
    public String toString() {
        return "JournalEntry@" + System.identityHashCode(this);
    }
}
