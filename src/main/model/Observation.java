package model;

//An observation regarding an experience in the body or mind
public abstract class Observation {
    protected int intensity;
    protected String note;

    //getters
    protected int getIntensity() {
        return this.intensity;
    }

    protected String getNote() {
        return this.note;
    }

    //setters
    //REQUIRES intensity is between 1 and 5
    protected void setIntensity(int intensity) {
        this.intensity = intensity;
    }

    //REQUIRES note is not blank
    protected void setNote(String note) {
        this.note = note;
    }

}
