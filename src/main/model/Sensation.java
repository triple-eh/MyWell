package model;

public abstract class Sensation {
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
    protected void setIntensity(int intensity) {
        this.intensity = intensity;
    }

    protected void addNote(String note) {
        this.note = note;
    }

}
