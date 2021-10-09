package model;

//is associated with a particular body part
public class PhysicalSensation extends Sensation {
    private String bodyPart;
    private String sensationType;

    //EFFECTS creates a new physical sensation with a body part
    public PhysicalSensation() {}

    //getters
    public String getBodyPart() {
        return this.bodyPart;
    }

    public String getSensationType() {
        return this.sensationType;
    }

    //REQUIRES Sensation type has to be one of: pleasant, unpleasant, neutral
    //EFFECTS adds a type of sensation
    public void addSensationType() {}

}
