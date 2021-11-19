package model;

//An observation regarding an experience in the body or mind
public abstract class Observation {
    protected Integer intensity;
    protected String note;

    //getters
    public int getIntensity() {
        return this.intensity;
    }

    public String getNote() {
        return this.note;
    }

    //setters
    //REQUIRES intensity is between 1 and 5
    public void setIntensity(Integer intensity) {
        this.intensity = intensity;
    }

    //REQUIRES note is not blank
    public void setNote(String note) {
        this.note = note;
    }

    //EFFECTS: prints the information about the observation
    public abstract String print();
}
