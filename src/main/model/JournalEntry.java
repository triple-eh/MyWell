package model;

import java.util.Date;
import java.util.List;

//creates a journal entry with a date and log of physical sensations and feelings
public class JournalEntry {
    private Date date;
    private String overallState;
    private List<PhysicalSensation> sensations;
    private List<Feeling> feelings;

    //two constructors - one with date, another without date both with overallState
    public JournalEntry() {

    }

    //getters
    public Date getDate() {
        return this.date;
    }

    public String getOverallState() {
        return this.overallState;
    }

    //EFFECTS if associated body part has a sensation already in current entry, returns false
    //        otherwise, adds the sensation to the entry
    public void addPhysicalSensation() {

    }

    //EFFECTS if feeling is already in current entry, returns false
    //        otherwise, adds the feeling to the entry
    public void addFeeling() {

    }

    //maybe have an update method?
}
