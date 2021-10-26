package model;

import java.util.Objects;

//A feeling experienced at the moment of the journal entry
public class Feeling extends Observation {
    private String feelingName;

    //EFFECTS creates a new feeling with a given name
    public Feeling(String feelingName) {
        this.feelingName = feelingName;
    }

    //getters
    public String getFeelingName() {
        return this.feelingName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Feeling feeling = (Feeling) o;
        return feelingName.equals(feeling.feelingName) && Objects.equals(intensity, feeling.intensity)
                && Objects.equals(note, feeling.note);
    }

    @Override
    public int hashCode() {
        return Objects.hash(feelingName, intensity, note);
    }
}
