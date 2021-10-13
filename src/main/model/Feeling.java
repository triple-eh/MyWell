package model;

//A feeling experienced at the moment of the journal entry
public class Feeling extends Observation {
    private String feelingName;

    //EFFECTS creates a new feeling with a given name
    public Feeling(String feelingName) {

    }

    //getters
    public String getFeeling() {
        return this.feelingName;
    }

}
